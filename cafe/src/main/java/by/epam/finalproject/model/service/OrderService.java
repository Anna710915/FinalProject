package by.epam.finalproject.model.service;

import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.entity.Menu;
import by.epam.finalproject.model.entity.Order;
import by.epam.finalproject.model.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface OrderService {
    boolean createOrder(Map<Menu, Integer> productMap, Map<String, String> orderInfo, User user, BigDecimal totalPrice) throws ServiceException;
    int calculateProductsNumberPerYear(long userId) throws ServiceException;
    List<Order> findAllUserOrders(User user) throws ServiceException;
    List<Order> findAllOrders() throws ServiceException;
    boolean changeOrderStateById(long orderId, Order.OrderState state) throws ServiceException;
}
