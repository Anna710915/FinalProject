package by.epam.finalproject.model.dao;

import by.epam.finalproject.exception.DaoException;
import by.epam.finalproject.model.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * The interface User dao.
 */
public interface UserDao {
    /**
     * Find password by login optional.
     *
     * @param login the login
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<String> findPasswordByLogin(String login) throws DaoException;

    /**
     * Update password by login boolean.
     *
     * @param password the password
     * @param login    the login
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updatePasswordByLogin(String password, String login) throws DaoException;

    /**
     * Update user state boolean.
     *
     * @param userId  the user id
     * @param stateId the state id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateUserState(long userId, long stateId) throws DaoException;

    /**
     * Find user by login optional.
     *
     * @param login the login
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findUserByLogin(String login) throws DaoException;

    /**
     * Find user by phone number optional.
     *
     * @param phone the phone
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findUserByPhoneNumber(int phone) throws DaoException;

    /**
     * Find user by email optional.
     *
     * @param email the email
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findUserByEmail(String email) throws DaoException;

    /**
     * Find user by order optional.
     *
     * @param orderId the order id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findUserByOrder(long orderId) throws DaoException;

    /**
     * Find user by login and password optional.
     *
     * @param login    the login
     * @param password the password
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    /**
     * Update user discount id by user id boolean.
     *
     * @param id         the id
     * @param discountId the discount id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateUserDiscountIdByUserId(long id, long discountId) throws DaoException;

    /**
     * Find all admins list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<User> findAllAdmins() throws DaoException;

    /**
     * Find all clients list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<User> findAllClients() throws DaoException;
}
