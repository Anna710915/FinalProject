package by.epam.finalproject.model.dao.impl;

import by.epam.finalproject.exception.DaoException;
import by.epam.finalproject.model.dao.AbstractDao;
import by.epam.finalproject.model.dao.OrderDao;
import by.epam.finalproject.model.entity.ComponentOrder;
import by.epam.finalproject.model.entity.Menu;
import by.epam.finalproject.model.entity.Order;
import by.epam.finalproject.model.entity.User;
import by.epam.finalproject.model.mapper.impl.OrderMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The type Order dao.
 */
public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {
    private static final Logger logger = LogManager.getLogger();
    private static final int ONE_UPDATE = 1;
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String SQL_SELECT_ALL_ORDERS = """
            SELECT order_id, order_date, order_state, type_payment, user_comment,
            total_cost, address, user_id FROM orders""";
    private static final String SQL_SELECT_ORDER_BY_ID = """
            SELECT order_id, order_date, order_state, type_payment, user_comment,
            total_cost, address, user_id FROM orders WHERE order_id = (?)""";
    private static final String SQL_INSERT_NEW_ORDER = """
            INSERT INTO orders(order_date, order_state, type_payment, user_comment,
            total_cost, address, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)""";
    private static final String SQL_DELETE_ORDER = """
            DELETE FROM orders WHERE order_id = (?)""";
    private static final String SQL_UPDATE_ORDER = """
            UPDATE orders SET order_date = (?), order_state = (?),
            type_payment = (?), user_comment = (?), total_cost = (?), address = (?), user_id = (?)
            WHERE order_id = (?)""";
    private static final String SQL_SELECT_ALL_USER_ORDERS = """
            SELECT order_id, order_date, order_state, type_payment, user_comment,
            total_cost, address, user_id FROM orders WHERE user_id = (?)""";
    private static final String SQL_UPDATE_ORDER_STATE_BY_ID = """
            UPDATE orders SET order_state = (?),
            order_date = (?) WHERE order_id = (?)""";
    private static final String SQL_INSERT_ORDER_MENU_BY_ORDER_ID = """
            INSERT INTO orders_menu(order_id, food_id, dish_number)
            VALUES (?, ?, ?)""";
    private static final String SQL_SELECT_THE_NUMBER_OF_YEAR_ORDERS_BY_USER_ID = """
            SELECT COUNT(*) FROM orders
            WHERE (order_date BETWEEN DATE_SUB(NOW(), INTERVAL 1 YEAR) AND NOW())
            AND (order_state = 'received')
            GROUP BY user_id
            HAVING user_id = (?)""";
    private static final String SQL_SELECT_ALL_ORDER_MENU = """
            SELECT name_food, dish_number FROM orders
            JOIN orders_menu ON orders_menu.order_id = orders.order_id
            JOIN menu ON menu.food_id = orders_menu.food_id
            WHERE orders.order_id = (?)""";
    private static final String SQL_DELETE_OLD_USERS_ORDERS = """
            DELETE FROM orders
            WHERE order_date < DATE_SUB(NOW(), INTERVAL 1 YEAR)""";


    @Override
    public List<Order> findAll() throws DaoException {
        List<Order> orderList = new ArrayList<>();
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_SELECT_ALL_ORDERS)) {
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Optional<Order> optionalOrder = new OrderMapper().mapRow(resultSet);
                    optionalOrder.ifPresent(orderList::add);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while find all orders ");
            throw new DaoException("Exception while find all orders ", e);
        }
        return orderList;
    }

    @Override
    public Optional<Order> findEntityById(long id) throws DaoException{
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_SELECT_ORDER_BY_ID)){
            statement.setLong(1,id);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new OrderMapper().mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while find order by id ");
            throw new DaoException("Exception while find order by id ", e);
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(long id) throws DaoException{
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_DELETE_ORDER)){
            statement.setLong(1,id);
            return statement.executeUpdate() == ONE_UPDATE;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while delete order by id ");
            throw new DaoException("Exception while delete order by id ", e);
        }
    }

    @Override
    public boolean create(Order entity) throws DaoException {
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_INSERT_NEW_ORDER)){
            statement.setDate(1, Date.valueOf(entity.getOrderDate().toString()));
            statement.setString(2, entity.getOrderState().getState());
            statement.setString(3, entity.getTypePayment().getPayment());
            statement.setString(4, entity.getUserComment());
            statement.setBigDecimal(5, entity.getTotalCost());
            statement.setString(6, entity.getAddress());
            statement.setLong(7, entity.getUserId());
            return statement.executeUpdate() == ONE_UPDATE;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while create order ");
            throw new DaoException("Exception while create order ", e);
        }
    }

    @Override
    public Optional<Order> update(Order entity) throws DaoException {
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_UPDATE_ORDER)){
            Optional<Order> order = findEntityById(entity.getOrderId());
            statement.setDate(1, Date.valueOf(entity.getOrderDate().toString()));
            statement.setString(2, entity.getOrderState().getState());
            statement.setString(3, entity.getTypePayment().getPayment());
            statement.setString(4, entity.getUserComment());
            statement.setBigDecimal(5, entity.getTotalCost());
            statement.setString(6, entity.getAddress());
            statement.setLong(7, entity.getUserId());
            statement.setLong(8, entity.getOrderId());
            return statement.executeUpdate() == ONE_UPDATE ? order : Optional.empty();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while update order ");
            throw new DaoException("Exception while update order ", e);
        }
    }

    @Override
    public long createOrder(Order order) throws DaoException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_INSERT_NEW_ORDER, Statement.RETURN_GENERATED_KEYS)){
            statement.setTimestamp(1, Timestamp.valueOf(order.getOrderDate().format(formatter)));
            statement.setString(2, order.getOrderState().getState());
            statement.setString(3, order.getTypePayment().getPayment());
            statement.setString(4, order.getUserComment());
            statement.setBigDecimal(5, order.getTotalCost());
            statement.setString(6, order.getAddress());
            statement.setLong(7, order.getUserId());
            statement.executeUpdate();
            try(ResultSet resultSet = statement.getGeneratedKeys()) {
                resultSet.next();
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while create order ");
            throw new DaoException("Exception while create order ", e);
        }
    }

    @Override
    public List<Order> findAllUserOrders(User user) throws DaoException {
        List<Order> orderList = new ArrayList<>();
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_SELECT_ALL_USER_ORDERS)){
            statement.setLong(1,user.getUserId());
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Optional<Order> optionalOrder = new OrderMapper().mapRow(resultSet);
                    optionalOrder.ifPresent(orderList::add);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while find all user orders ");
            throw new DaoException("Exception while find all user orders ", e);
        }
        return orderList;
    }


    @Override
    public boolean updateOrderStateById(long orderId, Order.OrderState orderState) throws DaoException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_UPDATE_ORDER_STATE_BY_ID)){
            statement.setString(1,orderState.getState());
            statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now().format(formatter)));
            statement.setLong(3, orderId);
            return statement.executeUpdate() == ONE_UPDATE;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while update order state by id ");
            throw new DaoException("Exception while update order state by id ", e);
        }
    }

    @Override
    public boolean createOrderMenu(long orderId, Map<Menu, Integer> mapOrderProduct)  throws DaoException{
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_INSERT_ORDER_MENU_BY_ORDER_ID)){
            for(Menu item: mapOrderProduct.keySet()){
                int value = mapOrderProduct.get(item);
                statement.setLong(1, orderId);
                statement.setLong(2, item.getFoodId());
                statement.setInt(3, value);
                statement.addBatch();
            }
            return statement.executeBatch().length >= ONE_UPDATE;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while create order-menu data ");
            throw new DaoException("Exception in a createOrderMenu method. ", e);
        }
    }

    @Override
    public int findNumberYearOrdersByUserId(long userId) throws DaoException {
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_SELECT_THE_NUMBER_OF_YEAR_ORDERS_BY_USER_ID)){
            statement.setLong(1, userId);
            try(ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() ? resultSet.getInt(1) : 0;
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while find number year orders by user id ");
            throw new DaoException("Exception in a findNumberYearOrdersByUserId method. ", e);
        }
    }

    @Override
    public List<ComponentOrder> findAllMenuOrder(long orderId) throws DaoException {
        List<ComponentOrder> componentOrders = new ArrayList<>();
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_SELECT_ALL_ORDER_MENU)){
            statement.setLong(1, orderId);
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String nameFood = resultSet.getString(1);
                    int amount = resultSet.getInt(2);
                    componentOrders.add(new ComponentOrder(nameFood, amount));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while find all menu order ");
            throw new DaoException("Exception in a findAllMenuOrder method. ", e);
        }
        return componentOrders;
    }

    @Override
    public boolean deleteOrders() throws DaoException {
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_DELETE_OLD_USERS_ORDERS)){
            return statement.executeUpdate() >= ONE_UPDATE;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while delete old orders ");
            throw new DaoException("Exception in a deleteOrders method. ", e);
        }
    }
}
