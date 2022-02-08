package by.epam.finalproject.model.dao.impl;

import by.epam.finalproject.exception.DaoException;
import by.epam.finalproject.model.dao.AbstractDao;
import by.epam.finalproject.model.dao.MenuDao;
import by.epam.finalproject.model.entity.Menu;
import by.epam.finalproject.model.mapper.impl.MenuMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.*;

/**
 * The type MenuDaoImpl class executes requests to the DB.
 */
public class MenuDaoImpl extends AbstractDao<Menu> implements MenuDao {
    private static final Logger logger = LogManager.getLogger();
    private static final int ONE_UPDATE = 1;
    private static final String SQL_SELECT_ALL_MENU = """
            SELECT food_id, name_food, picture_path, composition, weight,
            calories, cooking_time, discount, price, section_id, is_accessible FROM menu
            WHERE is_accessible = true""";
    private static final String SQL_SELECT_MENU_BY_ID = """
            SELECT food_id, name_food, picture_path, composition, weight,
            calories, cooking_time, discount, price, section_id, is_accessible FROM menu
            WHERE food_id = (?)""";
    private static final String SQL_INSERT_NEW_MENU_ITEM = """
            INSERT INTO menu(name_food, picture_path, composition, weight,
            calories, cooking_time, discount, price, section_id, is_accessible)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)""";
    private static final String SQL_DELETE_MENU_ITEM = """
            UPDATE menu
            SET is_accessible = false
            WHERE food_id = (?)""";
    private static final String SQL_DELETE_MENU_BY_SECTION_ID = """
            UPDATE menu
            SET is_accessible = false
            WHERE section_id = (?)""";
    private static final String SQL_UPDATE_MENU = """
            UPDATE menu SET name_food = (?), composition = (?),
            weight = (?), calories = (?), cooking_time = (?), discount = (?),
            price = (?), section_id = (?) WHERE food_id = (?)""";
    private static final String SQL_UPDATE_IMAGE_PATH_BY_NAME = """
            UPDATE menu SET picture_path = (?) WHERE name_food = (?)""";
    private static final String SQL_SELECT_MENU_BY_NAME = """
            SELECT food_id, name_food, picture_path, composition, weight,
            calories, cooking_time, discount, price, section_id, is_accessible FROM menu
            WHERE name_food = (?)""";
    private static final String SQL_FIND_MENU_SUBLIST_BY_SECTION_ID = """
            SELECT food_id, name_food, picture_path, composition, weight,
            calories, cooking_time, discount, price,  section_id, is_accessible FROM menu
            WHERE section_id = (?) AND is_accessible = true
            LIMIT ? OFFSET ?""";
    private static final String SQL_SELECT_ALL_MENU_ROW_COUNT = """
            SELECT COUNT(*) FROM menu
            WHERE is_accessible = true""";
    private static final String SQL_SELECT_MENU_SUBLIST = """
            SELECT food_id, name_food, picture_path, composition, weight,
            calories, cooking_time, discount, price, section_id, is_accessible FROM menu
            WHERE is_accessible = true
            LIMIT ? OFFSET ?""";
    private static final String SQL_SELECT_ALL_SORTED_MENU = """
            SELECT food_id, name_food, picture_path, composition, weight,
            calories, cooking_time, discount, price, section_id, is_accessible FROM menu
            WHERE is_accessible = true
            ORDER BY price - (price * discount)
            LIMIT ? OFFSET ?""";
    private static final String SQL_SELECT_ALL_SORTED_MENU_BY_POPULARITY = """
            SELECT menu.food_id, name_food, picture_path, composition, weight,
            calories, cooking_time, discount, price, section_id, is_accessible, all_dish FROM menu
            LEFT JOIN (SELECT food_id, SUM(dish_number) AS all_dish FROM orders_menu
            GROUP BY food_id) AS year_food ON year_food.food_id = menu.food_id
            WHERE is_accessible = true
            ORDER BY all_dish DESC
            LIMIT ? OFFSET ?""";
    private static final String SQL_SELECT_SORTED_SECTION_MENU = """
            SELECT food_id, name_food, picture_path, composition, weight,
            calories, cooking_time, discount, price, section_id, is_accessible FROM menu
            WHERE section_id = ? AND is_accessible = true
            ORDER BY price - (price * discount)
            LIMIT ? OFFSET ?""";
    private static final String SQL_SELECT_ALL_SORTED_SECTION_MENU_BY_POPULARITY = """
            SELECT menu.food_id, name_food, picture_path, composition, weight,
            calories, cooking_time, discount, price, section_id, is_accessible, all_dish FROM menu
            LEFT JOIN (SELECT food_id, SUM(dish_number) AS all_dish FROM orders_menu
            GROUP BY food_id) AS year_food ON year_food.food_id = menu.food_id
            WHERE is_accessible = true AND section_id = ?
            ORDER BY all_dish DESC
            LIMIT ? OFFSET ?""";
    private static final String SQL_SELECT_MENU_ROW_COUNT_BY_SECTION_ID = """
            SELECT COUNT(*) FROM menu WHERE section_id = ? AND is_accessible = true""";
    private static final String SQL_SELECT_ALL_REMOVING_MENU_PRODUCTS = """
            SELECT food_id, name_food, picture_path, composition, weight,
            calories, cooking_time, discount, price, menu.section_id, menu.is_accessible FROM menu
            JOIN sections ON sections.section_id = menu.section_id
            WHERE menu.is_accessible = false AND sections.is_accessible = true""";
    private static final String SQL_RESTORE_MENU_BY_PRODUCT_ID = """
            UPDATE menu
            SET is_accessible = true
            WHERE food_id = (?)""";
    private static final String SQL_RESTORE_MENU_BY_SECTION_ID = """
            UPDATE menu
            SET is_accessible = true
            WHERE section_id = (?)""";

    @Override
    public List<Menu> findAll() throws DaoException {
        List<Menu> menuList = new ArrayList<>();
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_SELECT_ALL_MENU);
            ResultSet resultSet = statement.executeQuery()){
            while (resultSet.next()){
                Optional<Menu> optionalMenu = new MenuMapper().mapRow(resultSet);
                if(optionalMenu.isPresent()) {
                    menuList.add(optionalMenu.get());
                    logger.log(Level.INFO,"Present");
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while find all menu method ");
            throw new DaoException("Exception while find all menu method ", e);
        }
        return menuList;
    }

    @Override
    public Optional<Menu> findEntityById(long id) throws DaoException {
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_SELECT_MENU_BY_ID)){
            statement.setLong(1,id);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new MenuMapper().mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while find menu by id method ");
            throw new DaoException("Exception while find menu by id method ", e);
        }
        logger.log(Level.INFO, "Menu item is empty ");
        return Optional.empty();
    }

    @Override
    public boolean delete(long id) throws DaoException {
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_DELETE_MENU_ITEM)) {
            statement.setLong(1,id);
            return statement.executeUpdate() == ONE_UPDATE;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while delete menu by id method ");
            throw new DaoException("Exception while delete menu by id method ", e);
        }
    }

    @Override
    public boolean create(Menu entity) throws DaoException {
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_INSERT_NEW_MENU_ITEM)){
            statement.setString(1,entity.getNameFood());
            statement.setString(2,entity.getPicturePath());
            statement.setString(3,entity.getComposition());
            statement.setDouble(4,entity.getWeight());
            statement.setDouble(5,entity.getCalories());
            statement.setTime(6, Time.valueOf(entity.getCookingTime()));
            statement.setBigDecimal(7,entity.getDiscount());
            statement.setBigDecimal(8,entity.getPrice());
            statement.setLong(9,entity.getSectionId());
            statement.setBoolean(10, entity.isAccessible());
            return statement.executeUpdate() == ONE_UPDATE;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while create menu method ");
            throw new DaoException("Exception while create menu method ", e);
        }
    }
    @Override
    public Optional<Menu> update(Menu entity) throws DaoException {
        try (PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_UPDATE_MENU)){
            Optional<Menu> menu = findEntityById(entity.getFoodId());
            logger.log(Level.INFO, entity.getNameFood());
            statement.setString(1,entity.getNameFood());
            logger.log(Level.INFO, entity.getComposition());
            statement.setString(2,entity.getComposition());
            logger.log(Level.INFO, entity.getWeight());
            statement.setDouble(3,entity.getWeight());
            logger.log(Level.INFO, entity.getCalories());
            statement.setDouble(4,entity.getCalories());
            logger.log(Level.INFO, Time.valueOf(entity.getCookingTime()));
            statement.setTime(5, Time.valueOf(entity.getCookingTime()));
            logger.log(Level.INFO, entity.getDiscount());
            statement.setBigDecimal(6,entity.getDiscount());
            logger.log(Level.INFO, entity.getPrice());
            statement.setBigDecimal(7,entity.getPrice());
            logger.log(Level.INFO, entity.getSectionId());
            statement.setLong(8, entity.getSectionId());
            logger.log(Level.INFO, entity.getFoodId());
            statement.setLong(9,entity.getFoodId());
            return statement.executeUpdate() == ONE_UPDATE ? menu : Optional.empty();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while update menu method ");
            throw new DaoException("Exception while update menu method ", e);
        }
    }

    @Override
    public boolean updateImagePathByName(String name, String filePath) throws DaoException {
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_UPDATE_IMAGE_PATH_BY_NAME)){
            statement.setString(1,filePath);
            statement.setString(2,name);
            return statement.executeUpdate() == ONE_UPDATE;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while update image path by name menu method ");
            throw new DaoException("Exception while update image path by name menu method ", e);
        }
    }

    @Override
    public Optional<Menu> findFoodByName(String name) throws DaoException {
        Optional<Menu> food = Optional.empty();
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_SELECT_MENU_BY_NAME)){
            statement.setString(1,name);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    food = new MenuMapper().mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while find food by name method ");
            throw new DaoException("Exception while find food by name method ", e);
        }
        return food;
    }

    @Override
    public List<Menu> findMenuSublistBySectionId(int pageSize, int offset, long sectionId) throws DaoException {
        List<Menu> menuList = new ArrayList<>();
        try (PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_FIND_MENU_SUBLIST_BY_SECTION_ID)){
            statement.setLong(1, sectionId);
            statement.setInt(2, pageSize);
            statement.setInt(3, offset);
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Optional<Menu> optionalMenu = new MenuMapper().mapRow(resultSet);
                    optionalMenu.ifPresent(menuList::add);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while find menu sublist by section id method ");
            throw new DaoException("Exception in a findMenuSublistBySection method. ", e);
        }
        return menuList;
    }

    @Override
    public int readRowCount() throws DaoException {
        try (PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_SELECT_ALL_MENU_ROW_COUNT)){
            try(ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() ? resultSet.getInt(1) : 0;
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while read row count from menu table method ");
            throw new DaoException("Exception in a readRowCount method. ", e);
        }
    }

    @Override
    public List<Menu> findMenuSublist(int pageSize, int offset) throws DaoException {
        List<Menu> menuSublist = new ArrayList<>();
        try (PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_SELECT_MENU_SUBLIST)){
            statement.setInt(1, pageSize);
            statement.setInt(2, offset);
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Optional<Menu> optionalMenu = new MenuMapper().mapRow(resultSet);
                    optionalMenu.ifPresent(menuSublist::add);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while find menu sublist method ");
            throw new DaoException("Exception in a findMenuSublist method. ", e);
        }
        return menuSublist;
    }

    @Override
    public List<Menu> findAllSortedMenuByPrice(int pageSize, int offset) throws DaoException {
        List<Menu> sortedList = new ArrayList<>();
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_SELECT_ALL_SORTED_MENU)){
            statement.setInt(1, pageSize);
            statement.setInt(2, offset);
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Optional<Menu> optionalMenu = new MenuMapper().mapRow(resultSet);
                    optionalMenu.ifPresent(sortedList::add);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while find sorted menu method ");
            throw new DaoException("Exception in a findAllSortedMenu method. ", e);
        }
        return sortedList;
    }

    @Override
    public List<Menu> findSortedSectionMenuByPrice(int pageSize, int offset, long sectionId) throws DaoException {
        List<Menu> sortedList = new ArrayList<>();
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_SELECT_SORTED_SECTION_MENU)) {
            statement.setLong(1, sectionId);
            statement.setInt(2, pageSize);
            statement.setInt(3, offset);
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Optional<Menu> optionalMenu = new MenuMapper().mapRow(resultSet);
                    optionalMenu.ifPresent(sortedList::add);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while find sorted menu sublist by section id method ");
            throw new DaoException("Exception in a findSortedSectionMenu method. ", e);
        }
        return sortedList;
    }
    @Override
    public List<Menu> findAllSortedMenuByPopularity(int pageSize, int offset) throws DaoException {
        List<Menu> menuList = new ArrayList<>();
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_SELECT_ALL_SORTED_MENU_BY_POPULARITY)){
            statement.setInt(1, pageSize);
            statement.setInt(2, offset);
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    Optional<Menu> optionalMenu = new MenuMapper().mapRow(resultSet);
                    optionalMenu.ifPresent(menuList::add);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while find sorted menu sublist by popularity method ");
            throw new DaoException("Exception in a findAllSortedMenuByPopularity method. ", e);
        }
        return menuList;
    }

    @Override
    public List<Menu> findAllSortedSectionMenuByPopularity(int pageSize, int offset, long sectionId) throws DaoException {
        List<Menu> menuList = new ArrayList<>();
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_SELECT_ALL_SORTED_SECTION_MENU_BY_POPULARITY)){
            statement.setLong(1, sectionId);
            statement.setInt(2, pageSize);
            statement.setInt(3, offset);
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    Optional<Menu> optionalMenu = new MenuMapper().mapRow(resultSet);
                    optionalMenu.ifPresent(menuList::add);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while find sorted menu sublist by section id and by popularity method ");
            throw new DaoException("Exception in a findAllSortedSectionMenuByPopularity method. ", e);
        }
        return menuList;
    }
    @Override
    public int readRowCountBySection(long sectionId) throws DaoException {
        try (PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_SELECT_MENU_ROW_COUNT_BY_SECTION_ID)){
            statement.setLong(1, sectionId);
            try(ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() ? resultSet.getInt(1) : 0;
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while read row count from menu by section table method ");
            throw new DaoException("Exception in a readRowCountBySection method. ", e);
        }
    }

    @Override
    public boolean deleteMenuBySectionId(long sectionId) throws DaoException {
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_DELETE_MENU_BY_SECTION_ID)){
            statement.setLong(1, sectionId);
            return statement.executeUpdate() >= ONE_UPDATE;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while delete menu by section id = " + sectionId);
            throw new DaoException("Exception while delete menu by section id = " + sectionId, e);
        }
    }

    @Override
    public boolean restoreMenuById(long menuId) throws DaoException {
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_RESTORE_MENU_BY_PRODUCT_ID)) {
            statement.setLong(1, menuId);
            return statement.executeUpdate() == ONE_UPDATE;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while restoring menu by product id ");
            throw new DaoException("Exception while restoring menu by product id ", e);
        }
    }

    @Override
    public List<Menu> findAllRemovingMenu() throws DaoException {
        List<Menu> menuList = new ArrayList<>();
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_SELECT_ALL_REMOVING_MENU_PRODUCTS);
            ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()){
                Optional<Menu> optionalMenu = new MenuMapper().mapRow(resultSet);
                optionalMenu.ifPresent(menuList::add);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while reading removing menu ");
            throw new DaoException("Exception while reading removing menu ", e);
        }
        return menuList;
    }

    @Override
    public boolean restoreAllMenuBySectionId(long sectionId) throws DaoException {
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_RESTORE_MENU_BY_SECTION_ID)) {
            statement.setLong(1, sectionId);
            return statement.executeUpdate() >= ONE_UPDATE;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while restoring menu by section id ");
            throw new DaoException("Exception while restoring menu by section id ", e);
        }
    }
}
