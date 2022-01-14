package by.epam.finalproject.model.dao;

import by.epam.finalproject.exception.DaoException;

public interface UserDiscountDao {
    long findDiscountIdByNumberOrders(int numberOrders) throws DaoException;
}
