package by.epam.finalproject.model.mapper.impl;

import by.epam.finalproject.exception.DaoException;
import by.epam.finalproject.model.entity.Menu;
import by.epam.finalproject.model.mapper.CustomRowMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static by.epam.finalproject.model.mapper.impl.SectionMapper.SECTION;

/**
 * The type Menu mapper.
 */
public class MenuMapper implements CustomRowMapper<Menu> {
    private static final Logger logger = LogManager.getLogger();
    /**
     * The constant FOOD_ID.
     */
    public static final String FOOD_ID = "food_id";
    /**
     * The constant NAME_FOOD.
     */
    public static final String NAME_FOOD = "name_food";
    /**
     * The constant PICTURE_PATH.
     */
    public static final String PICTURE_PATH = "picture_path";
    /**
     * The constant COMPOSITION.
     */
    public static final String COMPOSITION = "composition";
    /**
     * The constant WEIGHT.
     */
    public static final String WEIGHT = "weight";
    /**
     * The constant CALORIES.
     */
    public static final String CALORIES = "calories";
    /**
     * The constant COOKING_TIME.
     */
    public static final String COOKING_TIME = "cooking_time";
    /**
     * The constant DISCOUNT.
     */
    public static final String DISCOUNT = "discount";
    /**
     * The constant PRICE.
     */
    public static final String PRICE = "price";

    /**
     * The constant IS_ACCESSIBLE_MENU_PRODUCT.
     */
    public static final String IS_ACCESSIBLE_MENU_PRODUCT = "is_accessible";

    @Override
    public Optional<Menu> mapRow(ResultSet resultSet) throws DaoException {
        Menu menu = new Menu();
        Optional<Menu> optionalMenu;
        try{
            menu.setFoodId(resultSet.getLong(FOOD_ID));
            menu.setNameFood(resultSet.getString(NAME_FOOD));
            menu.setPicturePath(resultSet.getString(PICTURE_PATH));
            menu.setComposition(resultSet.getString(COMPOSITION));
            menu.setWeight(resultSet.getDouble(WEIGHT));
            menu.setCalories(resultSet.getDouble(CALORIES));
            menu.setCookingTime(resultSet.getTime(COOKING_TIME).toLocalTime());
            menu.setDiscount(resultSet.getBigDecimal(DISCOUNT));
            menu.setPrice(resultSet.getBigDecimal(PRICE));
            menu.setSectionId(resultSet.getLong(SECTION));
            logger.log(Level.INFO, "Accessible - " + resultSet.getBoolean(IS_ACCESSIBLE_MENU_PRODUCT));
            menu.setAccessible(resultSet.getBoolean(IS_ACCESSIBLE_MENU_PRODUCT));
            logger.log(Level.INFO, "Accessible - " + menu.isAccessible());
            optionalMenu = Optional.of(menu);
        } catch (SQLException e) {
            logger.log(Level.WARN, "Not found menu item! ");
            optionalMenu = Optional.empty();
        }
        return optionalMenu;
    }
}
