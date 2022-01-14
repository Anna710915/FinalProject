package by.epam.finalproject.model.dao;

import by.epam.finalproject.exception.DaoException;
import by.epam.finalproject.model.entity.User;

import java.util.Optional;

public interface UserDao {
    Optional<String> findPasswordByLogin(String login) throws DaoException;
    boolean updatePasswordByLogin(String password, String login) throws DaoException;
    boolean updateUserStateByLogin(User.UserState state, String login) throws DaoException;
    boolean updateUserState(long userId, long stateId) throws DaoException;
    Optional<User> findUserByLogin(String login) throws DaoException;
    Optional<User> findUserByPhoneNumber(int phone) throws DaoException;
    Optional<User> findUserByEmail(String email) throws DaoException;
    Optional<User> findUserByOrder(long orderId) throws DaoException;
    Optional<User.UserState> findStateById(long userId) throws DaoException;
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;
    boolean updateUserDiscountIdByUserId(long id, long discountId) throws DaoException;
}
