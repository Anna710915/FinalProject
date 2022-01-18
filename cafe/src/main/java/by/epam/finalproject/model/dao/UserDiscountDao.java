package by.epam.finalproject.model.dao;

import by.epam.finalproject.exception.DaoException;

/**
 * The interface User discount dao.
 */
public interface UserDiscountDao {
    /**
     * Find discount id by number orders long.
     *
     * @param numberOrders the number orders
     * @return the long
     * @throws DaoException the dao exception
     */
    long findDiscountIdByNumberOrders(int numberOrders) throws DaoException;
}
