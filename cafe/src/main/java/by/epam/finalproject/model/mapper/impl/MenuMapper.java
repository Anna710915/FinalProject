package by.epam.finalproject.model.mapper.impl;

import by.epam.finalproject.exception.DaoException;
import by.epam.finalproject.model.entity.Menu;
import by.epam.finalproject.model.mapper.CustomRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MenuMapper implements CustomRowMapper<Menu> {
    public static final String FOOD_ID = "food_id";
    public static final String NAME_FOOD = "name_food";
    public static final String PICTURE_PATH = "picture_path";
    public static final String COMPOSITION = "composition";
    public static final String WEIGHT = "weight";
    public static final String CALORIES = "calories";
    public static final String COOKING_TIME = "cooking_time";
    public static final String DISCOUNT = "discount";
    public static final String PRICE = "price";
    public static final String SECTION = "section_id";
    public static final String DISH_NUMBER = "dish_number";

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
            optionalMenu = Optional.of(menu);
        } catch (SQLException e) {
            optionalMenu = Optional.empty();

        }
        return optionalMenu;
    }
}
