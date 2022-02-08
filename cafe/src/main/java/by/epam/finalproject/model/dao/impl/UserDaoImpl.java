package by.epam.finalproject.model.dao.impl;

import by.epam.finalproject.exception.DaoException;
import by.epam.finalproject.model.dao.AbstractDao;
import by.epam.finalproject.model.dao.UserDao;
import by.epam.finalproject.model.entity.User;
import by.epam.finalproject.model.mapper.impl.UserMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.epam.finalproject.model.mapper.impl.UserMapper.PASSWORD;

/**
 * The type UserDaoImpl class executes requests to the DB.
 */
public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    private static final Logger logger = LogManager.getLogger();
    private static final int ONE_UPDATE = 1;
    private static final String SQL_SELECT_ALL_CLIENTS = """
            SELECT user_id, first_name, last_name, login, user_password, email, phone, birthday,
            discount_id, state, role_name FROM users
            JOIN user_state ON user_state.state_id = users.state_id
            JOIN user_role ON user_role.role_id = users.role_id
            WHERE role_name = 'client'""";
    private static final String SQL_SELECT_ALL_ADMINS = """
            SELECT user_id, first_name, last_name, login, user_password, email, phone, birthday,
            discount_id, state, role_name FROM users
            JOIN user_state ON user_state.state_id = users.state_id
            JOIN user_role ON user_role.role_id = users.role_id
            WHERE role_name = 'admin'""";
    private static final String SQL_INSERT_NEW_USER = """ 
            INSERT INTO users(first_name, last_name, login, user_password, email, phone, birthday,
            discount_id, state_id, role_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)""";
    private static final String SQL_SELECT_USER_BY_ID = """
            SELECT user_id, first_name, last_name, login, user_password, email, phone, birthday,
            discount_id, state, role_name FROM users
            JOIN user_state ON user_state.state_id = users.state_id
            JOIN user_role ON user_role.role_id = users.role_id
            WHERE user_id = (?)""";
    private static final String SQL_DELETE_USER_BY_ID = """
            DELETE FROM users WHERE user_id = (?)""";
    private static final String SQL_UPDATE_USER = """
            UPDATE users SET first_name = (?), last_name = (?), login = (?), user_password = (?), email = (?),
            phone = (?), birthday = (?), discount_id = (?), state_id = (?), role_id = (?)
            WHERE user_id = (?)""";
    /**
     * The constant SQL_SELECT_PASSWORD_BY_LOGIN.
     */
    public static final String SQL_SELECT_PASSWORD_BY_LOGIN = """
            SELECT user_password FROM users WHERE login = (?)""";
    private static final String SQL_UPDATE_PASSWORD_BY_LOGIN = """
            UPDATE users SET user_password = (?) WHERE login = (?)""";
    private static final String SQL_UPDATE_USER_STATE_BY_ID = """
            UPDATE users SET state_id = (?) WHERE user_id = (?)""";
    private static final String SQL_UPDATE_USER_DISCOUNT_ID = """
            UPDATE users SET discount_id = (?) WHERE user_id = (?)""";
    private static final String SQL_SELECT_USER_BY_LOGIN = """
            SELECT users.user_id, first_name, last_name, login, user_password, email, phone, birthday,
            discount_id, state, role_name FROM users
            JOIN user_state ON user_state.state_id = users.state_id
            JOIN user_role ON user_role.role_id = users.role_id WHERE login = (?)""";
    private static final String SQL_SELECT_USER_BY_PHONE_NUMBER = """
            SELECT users.user_id, first_name, last_name, login, user_password, email, phone, birthday,
            discount_id, state, role_name FROM users
            JOIN user_state ON user_state.state_id = users.state_id
            JOIN user_role ON user_role.role_id = users.role_id WHERE phone = (?)""";
    private static final String SQL_SELECT_USER_BY_EMAIL = """
            SELECT users.user_id, first_name, last_name, login, user_password, email, phone, birthday,
            discount_id, state, role_name FROM users
            JOIN user_state ON user_state.state_id = users.state_id
            JOIN user_role ON user_role.role_id = users.role_id WHERE email = (?)""";
    private static final String SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD = """
            SELECT users.user_id, first_name, last_name, login, user_password, email, phone, birthday,
            discount_id, state, role_name FROM users
            JOIN user_state ON user_state.state_id = users.state_id
            JOIN user_role ON user_role.role_id = users.role_id WHERE login = (?) AND user_password = (?)""";
    private static final String SQL_SELECT_USER_BY_ORDER_ID = """
            SELECT users.user_id, first_name, last_name, login, user_password, email, phone, birthday,
            discount_id, state, role_name FROM users
            JOIN user_state ON user_state.state_id = users.state_id
            JOIN user_role ON user_role.role_id = users.role_id
            JOIN orders ON users.user_id = orders.user_id
            WHERE order_id = (?)""";

    @Override
    public List<User> findAll() throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> findAllClients() throws DaoException {
        List<User> userList = new ArrayList<>();
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_SELECT_ALL_CLIENTS)){
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Optional<User> optionalUser = new UserMapper().mapRow(resultSet);
                    optionalUser.ifPresent(userList::add);
                }
            }
            logger.log(Level.INFO,"List: " + userList);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while find all clients method ");
            throw new DaoException("Exception in a findAllAdmins method", e);
        }
        return userList;
    }

    @Override
    public List<User> findAllAdmins() throws DaoException {
        List<User> userList = new ArrayList<>();
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_SELECT_ALL_ADMINS)){
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Optional<User> optionalUser = new UserMapper().mapRow(resultSet);
                    optionalUser.ifPresent(userList::add);
                }
            }
            logger.log(Level.INFO,"List: " + userList);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while find all admins method ");
            throw new DaoException("Exception in a findAllAdmins method", e);
        }
        return userList;
    }

    @Override
    public Optional<User> findEntityById(long id) throws DaoException {
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_SELECT_USER_BY_ID)){
            statement.setLong(1,id);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new UserMapper().mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while find user by id method ");
            throw new DaoException("Exception while find user by id method ", e);
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(long id) throws DaoException {
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_DELETE_USER_BY_ID)){
            statement.setLong(1,id);
            return statement.executeUpdate() == ONE_UPDATE;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while delete user by id method ");
            throw new DaoException("Exception while delete user by id method ", e);
        }
    }

    @Override
    public boolean create(User entity) throws DaoException {
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_INSERT_NEW_USER)){
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getLogin());
            statement.setString(4, entity.getPassword());
            statement.setString(5, entity.getEmail());
            statement.setInt(6, entity.getPhoneNumber());
            statement.setDate(7, Date.valueOf(entity.getBirthday().toString()));
            statement.setLong(8, entity.getDiscountId());
            statement.setLong(9, entity.getState().getStateId());
            statement.setLong(10, entity.getRole().getRoleId());
            logger.log(Level.INFO,"The new row: " + entity);
            return statement.executeUpdate() == ONE_UPDATE;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while create user method ");
            throw new DaoException("Exception while create user method ", e);
        }
    }

    @Override
    public Optional<User> update(User entity) throws DaoException {
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_UPDATE_USER)){
            Optional<User> user = findEntityById(entity.getUserId());
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getLogin());
            statement.setString(4, entity.getPassword());
            statement.setString(5, entity.getEmail());
            statement.setInt(6, entity.getPhoneNumber());
            statement.setDate(7, Date.valueOf(entity.getBirthday().toString()));
            statement.setLong(8, entity.getDiscountId());
            statement.setLong(9, entity.getState().getStateId());
            statement.setLong(10, entity.getRole().getRoleId());
            statement.setLong(11,entity.getUserId());
            return statement.executeUpdate() == ONE_UPDATE ? user : Optional.empty();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while update user method ");
            throw new DaoException("Exception while update user method ", e);
        }
    }

    @Override
    public Optional<String> findPasswordByLogin(String login) throws DaoException {
        Optional<String> password = Optional.empty();
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_SELECT_PASSWORD_BY_LOGIN)){
            statement.setString(1,login);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    password = Optional.of(resultSet.getString(PASSWORD));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while find password by login method ");
            throw new DaoException("Exception while findPasswordByLogin method ", e);
        }
        return password;
    }

    @Override
    public boolean updatePasswordByLogin(String password, String login) throws DaoException {
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_UPDATE_PASSWORD_BY_LOGIN)){
            statement.setString(1,password);
            statement.setString(2,login);
            return statement.executeUpdate() == ONE_UPDATE;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while update password by login method ");
            throw new DaoException("Exception while updatePasswordByLogin method ", e);
        }
    }

    @Override
    public boolean updateUserState(long userId, long stateId) throws DaoException {
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_UPDATE_USER_STATE_BY_ID)){
            statement.setLong(1,stateId);
            statement.setLong(2,userId);
            return statement.executeUpdate() == ONE_UPDATE;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while update user state method ");
            throw new DaoException("Exception while update user state method ", e);
        }
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws DaoException {
        Optional<User> user = Optional.empty();
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_SELECT_USER_BY_LOGIN)){
            statement.setString(1,login);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new UserMapper().mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while find user by login method ");
            throw new DaoException("Exception while findUserByLogin method ", e);
        }
        return user;
    }

    @Override
    public Optional<User> findUserByPhoneNumber(int phone) throws DaoException {
        Optional<User> user = Optional.empty();
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_SELECT_USER_BY_PHONE_NUMBER)){
            statement.setInt(1,phone);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new UserMapper().mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while find user by phone number method ");
            throw new DaoException("Exception while find user by phone number method ", e);
        }
        return user;
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws DaoException {
        Optional<User> user = Optional.empty();
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_SELECT_USER_BY_EMAIL)){
            statement.setString(1,email);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new UserMapper().mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while find user by email method ");
            throw new DaoException("Exception while find user by email method ", e);
        }
        return user;
    }

    @Override
    public Optional<User> findUserByOrder(long orderId) throws DaoException {
        Optional<User> user = Optional.empty();
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_SELECT_USER_BY_ORDER_ID)){
            statement.setLong(1,orderId);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new UserMapper().mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while find user by order method ");
            throw new DaoException("Exception while findUserByOrder method ", e);
        }
        return user;
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        Optional<User> user = Optional.empty();
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD)){
            statement.setString(1,login);
            statement.setString(2,password);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new UserMapper().mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while find user by login and password method ");
            throw new DaoException("Exception while find user by login and password method ", e);
        }
        logger.log(Level.INFO, "findUserByLoginAndPassword dao method");
        return user;
    }

    @Override
    public boolean updateUserDiscountIdByUserId(long userId, long discountId) throws DaoException {
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_UPDATE_USER_DISCOUNT_ID)){
            statement.setLong(1, discountId);
            statement.setLong(2, userId);
            return statement.executeUpdate() == ONE_UPDATE;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while update user discount id by user id method ");
            throw new DaoException("Exception in a updateUserDiscountIdByUserId method. ", e);
        }
    }
}
