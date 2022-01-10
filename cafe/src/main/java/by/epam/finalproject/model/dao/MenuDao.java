package by.epam.finalproject.model.dao;

import by.epam.finalproject.exception.DaoException;
import by.epam.finalproject.model.entity.Menu;
import by.epam.finalproject.model.entity.Order;

import java.util.Map;
import java.util.Optional;

public interface MenuDao {
    Optional<String> findImagePathByName(String name) throws DaoException;
    boolean updateImagePathByName(String name, String filePath) throws DaoException;
    Optional<Menu> findFoodByName(String name) throws DaoException;
    Map<Menu,Integer> findAllOrderFood(Order order) throws DaoException;
}
