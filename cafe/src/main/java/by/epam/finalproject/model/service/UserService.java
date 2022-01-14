package by.epam.finalproject.model.service;

import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    Optional<User> signIn(String login, String password) throws ServiceException;
    boolean userRegistration(Map<String,String> mapData, User.UserRole role) throws ServiceException;
    List<User> findAllUsers() throws ServiceException;
    boolean deleteUser(long id) throws ServiceException;
    Optional<User> updateUserProfile(User user, Map<String, String> updateData) throws ServiceException;
    boolean changePasswordByOldPassword(Map<String, String> map, User user) throws ServiceException;
    boolean changeUserStateById(User.UserState role, long id) throws ServiceException;
    Optional<User> findUserByOrder(long orderId) throws ServiceException;
}
