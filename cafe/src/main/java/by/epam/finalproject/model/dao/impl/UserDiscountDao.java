package by.epam.finalproject.model.dao.impl;

import by.epam.finalproject.exception.DaoException;
import by.epam.finalproject.model.dao.AbstractDao;
import by.epam.finalproject.model.entity.UserDiscount;
import by.epam.finalproject.model.mapper.impl.UserDiscountMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDiscountDao extends AbstractDao<UserDiscount> {
    private static final Logger logger = LogManager.getLogger();
    private static final String SQL_SELECT_ALL_DISCOUNTS =
            "SELECT discount_id, discount, year_orders FROM personal_discounts";
    private static final String SQL_SELECT_ENTITY_BY_ID =
            "SELECT discount_id, discount, year_orders FROM personal_discounts WHERE discount_id = ?";
    private static final String SQL_INSERT_NEW_DISCOUNT =
            "INSERT INTO personal_discounts(discount, year_orders) VALUES (?, ?)";
    private static final String SQL_UPDATE_ROW_DISCOUNT =
            "UPDATE personal_discounts SET discount = (?), year_orders = (?) WHERE discount_id = (?)";
    private static final String SQL_DELETE_BY_ID =
            "DELETE FROM personal_discounts WHERE discount_id = (?)";
    private static final String SELECT_USER_DISCOUNT_BY_NUMBER_ORDERS = """
            SELECT """; //TODO

    @Override
    public List<UserDiscount> findAll() throws DaoException {
        List<UserDiscount> userDiscountList = new ArrayList<>();
        PreparedStatement statement = null;
        try{
            statement = this.proxyConnection.prepareStatement(SQL_SELECT_ALL_DISCOUNTS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Optional<UserDiscount> optionalUserDiscount = new UserDiscountMapper().mapRow(resultSet);
                if(optionalUserDiscount.isPresent()) {
                    userDiscountList.add(optionalUserDiscount.get());
                }
            }
            logger.log(Level.INFO,"The list of discounts: " + userDiscountList.toString());
        }catch (SQLException e){
            throw new DaoException();
        }finally {
            close(statement);
        }
        return userDiscountList;
    }

    @Override
    public UserDiscount findEntityById(long id) throws DaoException {
        PreparedStatement statement = null;
        UserDiscount userDiscount = null;
        try{
            statement = this.proxyConnection.prepareStatement(SQL_SELECT_ENTITY_BY_ID);
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                Optional<UserDiscount> optionalUserDiscount = new UserDiscountMapper().mapRow(resultSet);
                if(optionalUserDiscount.isPresent()) {
                    userDiscount = optionalUserDiscount.get();
                }
            }
            logger.log(Level.INFO,"Entity: " + userDiscount);
        }catch (SQLException e){
            throw new DaoException(e);
        }finally {
            close(statement);
        }
        return userDiscount;
    }

    @Override
    public boolean delete(long id) throws DaoException {
        PreparedStatement statement = null;
        try{
            statement = this.proxyConnection.prepareStatement(SQL_DELETE_BY_ID);
            statement.setLong(1,id);
            return statement.executeUpdate() != 0 ? true : false;
        } catch (SQLException e) {
            throw new DaoException(e);
        }finally {
            close(statement);
        }
    }

    @Override
    public boolean delete(UserDiscount entity) throws DaoException {
        PreparedStatement statement = null;
        try{
            UserDiscount userDiscount = findEntityById(entity.getDiscountId());
            if(userDiscount.equals(entity)) {
                statement = this.proxyConnection.prepareStatement(SQL_DELETE_BY_ID);
                statement.setLong(1, entity.getDiscountId());
                return statement.executeUpdate() != 0 ? true : false;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }finally {
            close(statement);
        }
        return false;
    }

    @Override
    public boolean create(UserDiscount entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = this.proxyConnection.prepareStatement(SQL_INSERT_NEW_DISCOUNT);
            statement.setBigDecimal(1,entity.getDiscount());
            statement.setInt(2,entity.getYearOrders());
            int rowUpdate = statement.executeUpdate();
            logger.log(Level.INFO, "rowUpdate equals: " + rowUpdate);
            return rowUpdate != 0 ? true : false;
        } catch (SQLException e) {
            throw new DaoException(e);
        }finally {
            close(statement);
        }
    }

    @Override
    public UserDiscount update(UserDiscount entity) throws DaoException {
        PreparedStatement statement = null;
        UserDiscount userDiscount;
        try{
            userDiscount = findEntityById(entity.getDiscountId());
            statement = this.proxyConnection.prepareStatement(SQL_UPDATE_ROW_DISCOUNT);
            statement.setBigDecimal(1,entity.getDiscount());
            statement.setInt(2,entity.getYearOrders());
            statement.setLong(3,entity.getDiscountId());
            logger.log(Level.INFO,"The new row: " + entity);
            return statement.executeUpdate() != 0 ? userDiscount  : null;
        }catch (SQLException e) {
            throw new DaoException(e);
        }finally {
            close(statement);
        }
    }
}
