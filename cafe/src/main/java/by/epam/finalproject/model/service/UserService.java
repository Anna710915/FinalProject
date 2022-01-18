package by.epam.finalproject.model.service;

import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface User service.
 */
public interface UserService {
    /**
     * Sign in optional.
     *
     * @param login    the login
     * @param password the password
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> signIn(String login, String password) throws ServiceException;

    /**
     * User registration boolean.
     *
     * @param mapData the map data
     * @param role    the role
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean userRegistration(Map<String,String> mapData, User.UserRole role) throws ServiceException;

    /**
     * Find all clients list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findAllClients() throws ServiceException;

    /**
     * Delete user boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean deleteUser(long id) throws ServiceException;

    /**
     * Update user profile optional.
     *
     * @param user       the user
     * @param updateData the update data
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> updateUserProfile(User user, Map<String, String> updateData) throws ServiceException;

    /**
     * Change password by old password boolean.
     *
     * @param map  the map
     * @param user the user
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean changePasswordByOldPassword(Map<String, String> map, User user) throws ServiceException;

    /**
     * Change user state by id boolean.
     *
     * @param role the role
     * @param id   the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean changeUserStateById(User.UserState role, long id) throws ServiceException;

    /**
     * Find all admins list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findAllAdmins() throws ServiceException;
}
