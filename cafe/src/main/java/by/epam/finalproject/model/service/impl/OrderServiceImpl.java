package by.epam.finalproject.model.service.impl;

import by.epam.finalproject.exception.DaoException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.dao.AbstractDao;
import by.epam.finalproject.model.dao.EntityTransaction;
import by.epam.finalproject.model.dao.impl.OrderDaoImpl;
import by.epam.finalproject.model.dao.impl.UserDaoImpl;
import by.epam.finalproject.model.entity.Menu;
import by.epam.finalproject.model.entity.Order;
import by.epam.finalproject.model.entity.User;
import by.epam.finalproject.model.service.OrderService;
import by.epam.finalproject.util.mail.Mail;
import by.epam.finalproject.validator.Validator;
import by.epam.finalproject.validator.impl.ValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.epam.finalproject.controller.Parameter.*;

public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LogManager.getLogger();
    private static final String EMAIL_ORDER_MESSAGE = "The order has been successfully placed";
    private static final String EMAIL_SUBJECT = "GoodCafe order";
    private static OrderServiceImpl instance;
    private final Validator validator = ValidatorImpl.getInstance();
    private OrderServiceImpl(){}

    public static OrderServiceImpl getInstance(){
        if(instance == null){
            instance = new OrderServiceImpl();
        }
        return instance;
    }
    @Override
    public boolean createOrder(Map<Menu, Integer> productMap, Map<String, String> orderInfo, User user, BigDecimal totalPrice) throws ServiceException {
        if(!validator.checkOrderInfo(orderInfo)){
            return false;
        }
        OrderDaoImpl orderDao = new OrderDaoImpl();
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(orderDao, userDao);
        String address = orderInfo.get(ADDRESS);
        String payment = orderInfo.get(PRODUCT_PAYMENT);
        String comment = orderInfo.get(USER_COMMENT);
        try{
            Order newOrder = new Order(LocalDateTime.now(), Order.OrderState.NEW, Order.TypePayment.valueOf(payment),
                    address, totalPrice, comment, user.getUserId());
            logger.log(Level.INFO, newOrder);
            long generatedKey = orderDao.createOrder(newOrder);
            if(generatedKey == 0){
                return false;
            }
            logger.log(Level.INFO, "generatedKey = " + generatedKey);
            orderDao.createOrderMenu(generatedKey, productMap);
            logger.log(Level.INFO, "createOrderMenu");
            userDao.updateUserState(user.getUserId(), User.UserState.ACTIVE.getStateId());
            logger.log(Level.INFO, "updateUserState");
            Mail.createMail(user.getEmail(), EMAIL_SUBJECT, EMAIL_ORDER_MESSAGE);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException("Exception in a createOrder method. ", e);
        } finally {
            transaction.endTransaction();
        }
        return true;
    }

    @Override
    public int calculateProductsNumberPerYear(long userId) throws ServiceException {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(orderDao);
        try {
            return orderDao.findNumberYearOrdersByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException("Exception in a calculateProductsNumberPerYear method. ", e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public List<Order> findAllUserOrders(User user) throws ServiceException {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(orderDao);
        try {
            return orderDao.findAllUserOrders(user);
        } catch (DaoException e) {
            throw new ServiceException("Exception in a findAllUserOrders method. ", e);
        }finally {
            transaction.end();
        }
    }

    @Override
    public List<Order> findAllOrders() throws ServiceException {
        AbstractDao<Order> orderDao = new OrderDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(orderDao);
        try {
            return orderDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Exception in a findAllOrders method. ", e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public boolean changeOrderStateById(long orderId, Order.OrderState state) throws ServiceException { //TODO
        OrderDaoImpl orderDao = new OrderDaoImpl();
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(orderDao, userDao);
        try{
            boolean result;
            switch (state){
                case PROCESSING, COMPLETED, CANCELLED ->
                        result = orderDao.updateOrderStateById(orderId, state);
                case RECEIVED -> {
                    result = orderDao.updateOrderStateById(orderId,state);
                    Optional<User> optionalUser = userDao.findUserByOrder(orderId);
                    if(optionalUser.isPresent()){
                        User user = optionalUser.get();
                        int numberOrders = orderDao.findNumberYearOrdersByUserId(user.getUserId());
                        userDao.updateUserDiscountByUserId(user.getUserId(), numberOrders);
                    }
                }
                default -> result = false;
            }
            transaction.commit();
            return result;
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException("Exception in a changeOrderStateById method. ", e);
        } finally {
            transaction.endTransaction();
        }
    }
}
