package by.epam.finalproject.model.dao.impl;

import by.epam.finalproject.exception.DaoException;
import by.epam.finalproject.model.dao.AbstractDao;
import by.epam.finalproject.model.dao.MenuDao;
import by.epam.finalproject.model.entity.Menu;
import by.epam.finalproject.model.entity.Order;
import by.epam.finalproject.model.mapper.impl.MenuMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.*;

import static by.epam.finalproject.controller.Parameter.*;
import static by.epam.finalproject.controller.Parameter.PRODUCT_SECTION;
import static by.epam.finalproject.model.mapper.impl.MenuMapper.DISH_NUMBER;
import static by.epam.finalproject.model.mapper.impl.MenuMapper.PICTURE_PATH;

public class MenuDaoImpl extends AbstractDao<Menu> implements MenuDao {
    private static final Logger logger = LogManager.getLogger();
    private static final String SQL_SELECT_ALL_MENU = """
            SELECT food_id, name_food, picture_path, composition, weight,
            calories, cooking_time, discount, price, section_id FROM menu""";
    private static final String SQL_SELECT_MENU_BY_ID = """
            SELECT food_id, name_food, picture_path, composition, weight,
            calories, cooking_time, discount, price, section_id FROM menu
            WHERE food_id = (?)""";
    private static final String SQL_INSERT_NEW_MENU_ITEM = """
            INSERT INTO menu(name_food, picture_path, composition, weight,
            calories, cooking_time, discount, price, section_id) 
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)""";
    private static final String SQL_DELETE_MENU_ITEM = """
            DELETE FROM menu WHERE food_id = (?)""";
    private static final String SQL_UPDATE_MENU = """
            UPDATE menu SET name_food = (?), picture_path = (?), composition = (?),
            weight = (?), calories = (?), cooking_time = (?), discount = (?),
            price = (?), section_id = (?) WHERE food_id = (?)""";
    private static final String SQL_SELECT_IMAGE_PATH_BY_NAME = """
            SELECT picture_path FROM menu WHERE name_food = (?)""";
    private static final String SQL_UPDATE_IMAGE_PATH_BY_NAME = """
            UPDATE menu SET picture_path = (?) WHERE name_food = (?)""";
    private static final String SQL_SELECT_MENU_BY_NAME = """
            SELECT food_id, name_food, picture_path, composition, weight,
            calories, cooking_time, discount, price, section_id FROM menu
            WHERE name_food = (?)""";
    private static final String SQL_SELECT_ALL_ORDER_FOOD = """
            SELECT food_id, name_food, picture_path, composition, weight,
            calories, cooking_time, discount, price,  section_id, dish_number FROM menu
            JOIN orders_menu ON orders_menu.food_id = menu.food_id
            WHERE orders_menu.order_id = (?)""";
    private static final String SQL_FIND_MENU_SUBLIST_BY_SECTION_ID = """
            SELECT food_id, name_food, picture_path, composition, weight,
            calories, cooking_time, discount, price,  section_id FROM menu
            WHERE section_id = (?)
            LIMIT ? OFFSET ?""";
    private static final String SQL_SELECT_ALL_MENU_ROW_COUNT = """
            SELECT COUNT(*) FROM menu""";
    private static final String SQL_SELECT_MENU_SUBLIST = """
            SELECT food_id, name_food, picture_path, composition, weight,
            calories, cooking_time, discount, price, section_id FROM menu
            LIMIT ? OFFSET ?""";

    @Override
    public List<Menu> findAll() throws DaoException {
        List<Menu> menuList = new ArrayList<>();
        PreparedStatement statement = null;
        try{
            statement = this.proxyConnection.prepareStatement(SQL_SELECT_ALL_MENU);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Optional<Menu> optionalMenu = new MenuMapper().mapRow(resultSet);
                if(optionalMenu.isPresent()) {
                    menuList.add(optionalMenu.get());
                    logger.log(Level.INFO,"Present");
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }finally {
            close(statement);
        }
        return menuList;
    }

    @Override
    public Menu findEntityById(long id) throws DaoException {
        Menu menu = null;
        PreparedStatement statement = null;
        try{
            statement = this.proxyConnection.prepareStatement(SQL_SELECT_MENU_BY_ID);
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                Optional<Menu> optionalMenu = new MenuMapper().mapRow(resultSet);
                if(optionalMenu.isPresent()) {
                    menu = optionalMenu.get();
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return menu;
    }

    @Override
    public boolean delete(long id) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = this.proxyConnection.prepareStatement(SQL_DELETE_MENU_ITEM);
            statement.setLong(1,id);
            return statement.executeUpdate() != 0 ? true : false;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
    }

    @Override
    public boolean delete(Menu entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = this.proxyConnection.prepareStatement(SQL_DELETE_MENU_ITEM);
            statement.setLong(1,entity.getFoodId());
            return statement.executeUpdate() != 0 ? true : false;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
    }

    @Override
    public boolean create(Menu entity) throws DaoException {
        PreparedStatement statement = null;
        try{
            statement = this.proxyConnection.prepareStatement(SQL_INSERT_NEW_MENU_ITEM);
            statement.setString(1,entity.getNameFood());
            statement.setString(2,entity.getPicturePath());
            statement.setString(3,entity.getComposition());
            statement.setDouble(4,entity.getWeight());
            statement.setDouble(5,entity.getCalories());
            statement.setTime(6, Time.valueOf(entity.getCookingTime()));
            statement.setBigDecimal(7,entity.getDiscount());
            statement.setBigDecimal(8,entity.getPrice());
            statement.setLong(9,entity.getSectionId());
            return statement.executeUpdate() != 0 ? true : false;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
    }
    @Override
    public Menu update(Menu entity) throws DaoException {
        PreparedStatement statement = null;
        Menu menu;
        try{
            menu = findEntityById(entity.getFoodId());
            statement = this.proxyConnection.prepareStatement(SQL_UPDATE_MENU);
            statement.setString(1,entity.getNameFood());
            statement.setString(2,entity.getPicturePath());
            statement.setString(3,entity.getComposition());
            statement.setDouble(4,entity.getWeight());
            statement.setDouble(5,entity.getCalories());
            statement.setTime(6, Time.valueOf(entity.getCookingTime()));
            statement.setBigDecimal(7,entity.getDiscount());
            statement.setBigDecimal(8,entity.getPrice());
            statement.setLong(9,entity.getSectionId());
            statement.setLong(10,entity.getFoodId());
            return statement.executeUpdate() != 0 ? menu : null;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
    }

    @Override
    public Optional<String> findImagePathByName(String name) throws DaoException {
        PreparedStatement statement = null;
        Optional<String> imagePath = Optional.empty();
        try{
            statement = this.proxyConnection.prepareStatement(SQL_SELECT_IMAGE_PATH_BY_NAME);
            statement.setString(1,name);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                imagePath = Optional.of(resultSet.getString(PICTURE_PATH));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return imagePath;
    }

    @Override
    public boolean updateImagePathByName(String name, String filePath) throws DaoException {
        PreparedStatement statement = null;
        try{
            statement = this.proxyConnection.prepareStatement(SQL_UPDATE_IMAGE_PATH_BY_NAME);
            statement.setString(1,filePath);
            statement.setString(2,name);
            return statement.executeUpdate() == 1 ? true : false;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
    }

    @Override
    public Optional<Menu> findFoodByName(String name) throws DaoException {
        PreparedStatement statement = null;
        Optional<Menu> food = Optional.empty();
        try{
            statement = this.proxyConnection.prepareStatement(SQL_SELECT_MENU_BY_NAME);
            statement.setString(1,name);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                food = new MenuMapper().mapRow(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return food;
    }

    @Override
    public Map<Menu, Integer> findAllOrderFood(Order order) throws DaoException {
        Map<Menu,Integer> orderMenu = new HashMap<>();
        PreparedStatement statement = null;
        try{
            statement = this.proxyConnection.prepareStatement(SQL_SELECT_ALL_ORDER_FOOD);
            statement.setLong(1,order.getOrderId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Optional<Menu> optionalMenu = new MenuMapper().mapRow(resultSet);
                if(optionalMenu.isPresent()) {
                    orderMenu.put(optionalMenu.get(), resultSet.getInt(DISH_NUMBER));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
        }
        return orderMenu;
    }

    @Override
    public List<Menu> findMenuSublistBySectionId(int pageSize, int offset, long sectionId) throws DaoException {
        PreparedStatement statement = null;
        List<Menu> menuList = new ArrayList<>();
        try {
            statement = this.proxyConnection.prepareStatement(SQL_FIND_MENU_SUBLIST_BY_SECTION_ID);
            statement.setLong(1, sectionId);
            statement.setInt(2, pageSize);
            statement.setInt(3, offset);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Optional<Menu> optionalMenu = new MenuMapper().mapRow(resultSet);
                if(optionalMenu.isPresent()){
                    menuList.add(optionalMenu.get());
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Exception in a findMenuSublistBySection method. ", e);
        } finally {
            close(statement);
        }
        return menuList;
    }

    @Override
    public int readRowCount() throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = this.proxyConnection.prepareStatement(SQL_SELECT_ALL_MENU_ROW_COUNT);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next() ? resultSet.getInt(1) : 0;
        } catch (SQLException e) {
            throw new DaoException("Exception in a readRowCount method. ", e);
        } finally {
            close(statement);
        }
    }

    @Override
    public List<Menu> findMenuSublist(int pageSize, int offset) throws DaoException {
        PreparedStatement statement = null;
        List<Menu> menuSublist = new ArrayList<>();
        try {
            statement = this.proxyConnection.prepareStatement(SQL_SELECT_MENU_SUBLIST);
            statement.setInt(1, pageSize);
            statement.setInt(2, offset);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Optional<Menu> optionalMenu = new MenuMapper().mapRow(resultSet);
                if(optionalMenu.isPresent()){
                    menuSublist.add(optionalMenu.get());
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Exception in a findMenuSublist method. ", e);
        } finally {
            close(statement);
        }
        return menuSublist;
    }
}
