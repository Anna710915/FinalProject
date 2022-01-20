package by.epam.finalproject.model.service.impl;

import by.epam.finalproject.exception.DaoException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.dao.EntityTransaction;
import by.epam.finalproject.model.dao.impl.UserDaoImpl;
import by.epam.finalproject.model.entity.User;
import by.epam.finalproject.model.service.UserService;
import by.epam.finalproject.util.PasswordEncryption;
import by.epam.finalproject.util.mail.Mail;
import by.epam.finalproject.validator.Validator;
import by.epam.finalproject.validator.impl.ValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.epam.finalproject.controller.Parameter.*;

/**
 * The type User service.
 */
public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();
    private static final String REGISTRATION_SUBJECT = "GoodCafe registration";
    private static final String REGISTRATION_BODY = "Registration was successful";
    private static final UserServiceImpl instance = new UserServiceImpl();
    private final Validator validator = ValidatorImpl.getInstance();

    private UserServiceImpl(){}

    /**
     * Get instance user service.
     *
     * @return the user service
     */
    public static UserServiceImpl getInstance(){
        return instance;
    }

    public Optional<User> signIn(String login, String password) throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(userDao);
        String encryptPassword = PasswordEncryption.md5Apache(password);
        try{
            return userDao.findUserByLoginAndPassword(login, encryptPassword);
        } catch (DaoException e) {
            throw new ServiceException("Exception in a signIn service method " , e);
        } finally {
            transaction.end();
        }
    }

    public boolean userRegistration(Map<String,String> mapData, User.UserRole role) throws ServiceException{
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(userDao);
        try {
            boolean commonResult = validator.checkRegistration(mapData);
            logger.log(Level.INFO, "Common result " + commonResult);
            if(!commonResult){
                return false;
            }
            String firstName = mapData.get(USER_FIRST_NAME);
            String lastName = mapData.get(USER_LAST_NAME);
            String login = mapData.get(LOGIN);
            String email = mapData.get(USER_EMAIL);
            String phone = mapData.get(USER_PHONE_NUMBER);
            String password = mapData.get(PASSWORD);
            String birthday = mapData.get(USER_BIRTHDAY);
            String encryptPassword = PasswordEncryption.md5Apache(password);
            int phoneNumber = Integer.parseInt(phone);
            LocalDate date = LocalDate.parse(birthday);
            long discountId = 1;
            boolean uniqResult = true;
            if(userDao.findUserByLogin(login).isPresent()){
                mapData.put(LOGIN,NOT_UNIQ_LOGIN);
                uniqResult = false;
            }
            if(userDao.findUserByEmail(email).isPresent()){
                mapData.put(USER_EMAIL,NOT_UNIQ_EMAIL);
                uniqResult = false;
            }
            if(userDao.findUserByPhoneNumber(phoneNumber).isPresent()){
                mapData.put(USER_PHONE_NUMBER,NOT_UNIQ_PHONE);
                uniqResult = false;
            }
            if(!uniqResult){
                return false;
            }

            User user = new User(firstName, lastName, login, encryptPassword, email,
                    phoneNumber, date, discountId, role, User.UserState.NEW);
            boolean isUserCreate = userDao.create(user);
            if(isUserCreate){
                Mail.createMail(email, REGISTRATION_SUBJECT, REGISTRATION_BODY);
            }
            return isUserCreate;
        } catch (DaoException e) {
            throw new ServiceException("Add user error: userRegistration service method ", e);
        } finally {
            transaction.end();
        }
    }

    public List<User> findAllClients() throws ServiceException{
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(userDao);
        try {
            return userDao.findAllClients();
        } catch (DaoException e) {
            throw new ServiceException("Exception in a findAllClients service method ", e);
        } finally {
            transaction.end();
        }
    }

    public boolean deleteAdmin(long id) throws ServiceException{
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(userDao);
        try {
            return userDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException("Exception in a deleteUser service method ", e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public Optional<User> updateUserProfile(User user, Map<String, String> updateData) throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(userDao);
        try{
            if(!validator.checkUpdateProfile(updateData)){
                logger.log(Level.INFO, "checkUpdateProfile is false");
                return Optional.empty();
            }
            String firstName = updateData.get(USER_FIRST_NAME);
            String lastName = updateData.get(USER_LAST_NAME);
            String email = updateData.get(USER_EMAIL);
            String phone = updateData.get(USER_PHONE_NUMBER);
            String birthday = updateData.get(USER_BIRTHDAY);
            logger.log(Level.INFO, "birthday - " + birthday);
            int phoneNumber = Integer.parseInt(phone);
            LocalDate date = LocalDate.parse(birthday);
            logger.log(Level.INFO, "LocalDate birthday - " + birthday);
            boolean uniqResult = true;
            if(userDao.findUserByEmail(email).isPresent() && !email.equals(user.getEmail())){
                updateData.put(USER_EMAIL,NOT_UNIQ_EMAIL);
                uniqResult = false;
            }

            if(userDao.findUserByPhoneNumber(phoneNumber).isPresent() && phoneNumber != user.getPhoneNumber()){
                updateData.put(USER_PHONE_NUMBER,NOT_UNIQ_PHONE);
                uniqResult = false;
            }

            if(!uniqResult){
                return Optional.empty();
            }

            User newUser = new User(user.getUserId(), firstName, lastName, user.getLogin(),
                    user.getPassword(), email, phoneNumber, date, user.getDiscountId(),
                    user.getRole(), user.getState());

            Optional<User> optionalUser = userDao.update(newUser);

            return optionalUser.isPresent() ? Optional.of(newUser) : Optional.empty();
        } catch (DaoException e) {
            throw new ServiceException("Exception in a updateUserProfile service method ", e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public boolean changePasswordByOldPassword(Map<String, String> map, User user) throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(userDao);
        String oldPassword = map.get(OLD_PASSWORD);
        String newPassword = map.get(NEW_PASSWORD);
        String repeatPassword = map.get(REPEAT_PASSWORD);
        try {
            if(!validator.isCorrectPassword(newPassword)){
                map.put(NEW_PASSWORD, INVALID_NEW_PASSWORD);
                return false;
            }

            Optional<String> optionalPassword = userDao.findPasswordByLogin(user.getLogin());
            if(optionalPassword.isPresent()){
                if(!optionalPassword.get().equals(PasswordEncryption.md5Apache(oldPassword))){
                    map.put(OLD_PASSWORD, INVALID_OLD_PASSWORD);
                    return false;
                }
            }

            if(!newPassword.equals(repeatPassword)){
                map.put(REPEAT_PASSWORD, INVALID_REPEAT_PASSWORD);
                return false;
            }
            String encryptNewPassword = PasswordEncryption.md5Apache(newPassword);
            boolean result = userDao.updatePasswordByLogin(encryptNewPassword, user.getLogin());
            user.setPassword(encryptNewPassword);
            return result;
        } catch (DaoException e) {
            throw new ServiceException("Exception in a changePasswordByOldPassword service method ", e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public boolean changeUserStateById(User.UserState state, long id) throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(userDao);
        try {
            return userDao.updateUserState(id, state.getStateId());
        } catch (DaoException e) {
            throw new ServiceException("Exception in a ChangeUserStateById method ", e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public List<User> findAllAdmins() throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(userDao);
        try {
            return userDao.findAllAdmins();
        } catch (DaoException e) {
            throw new ServiceException("Exception in a findAllAdmins method. ", e);
        } finally {
            transaction.end();
        }
    }
}
