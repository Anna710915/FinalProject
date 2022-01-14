package by.epam.finalproject.model.dao;

import by.epam.finalproject.exception.DaoException;
import by.epam.finalproject.model.entity.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrderDao {
    List<Order> findAllUserOrders(User user) throws DaoException;
    boolean updateOrderStateById(long orderId, Order.OrderState orderState) throws DaoException;
    Optional<Order.OrderState> findOrderStateById(long orderId) throws DaoException;
    boolean createOrderMenu(long orderId, Map<Menu, Integer> mapOrderProduct) throws DaoException;
    long createOrder(Order order) throws DaoException;
    int findNumberYearOrdersByUserId(long userId) throws DaoException;
    List<ComponentOrder> findAllMenuOrder(long orderId) throws DaoException;
}
