package by.epam.finalproject.model.service;

import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.entity.CompleteOrder;
import by.epam.finalproject.model.entity.Menu;
import by.epam.finalproject.model.entity.Order;
import by.epam.finalproject.model.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * The interface Order service.
 */
public interface OrderService {
    /**
     * Create order boolean.
     *
     * @param productMap the product map
     * @param orderInfo  the order info
     * @param user       the user
     * @param totalPrice the total price
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean createOrder(Map<Menu, Integer> productMap, Map<String, String> orderInfo, User user, BigDecimal totalPrice) throws ServiceException;

    /**
     * Calculate products number per year int.
     *
     * @param userId the user id
     * @return the int
     * @throws ServiceException the service exception
     */
    int calculateProductsNumberPerYear(long userId) throws ServiceException;

    /**
     * Find all user orders list.
     *
     * @param user the user
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findAllUserOrders(User user) throws ServiceException;

    /**
     * Find all orders list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<CompleteOrder> findAllOrders() throws ServiceException;

    /**
     * Change order state by id boolean.
     *
     * @param orderId the order id
     * @param state   the state
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean changeOrderStateById(long orderId, Order.OrderState state) throws ServiceException;

    /**
     * Delete old orders boolean.
     *
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean deleteOldOrders() throws ServiceException;

    /**
     * Find all sorted orders by state list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findAllSortedOrdersByDate() throws ServiceException;
}
