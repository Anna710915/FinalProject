package by.epam.finalproject.controller.command.impl.common;

import by.epam.finalproject.controller.Router;
import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.exception.CommandException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.entity.User;
import by.epam.finalproject.model.service.UserService;
import by.epam.finalproject.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import static by.epam.finalproject.controller.Parameter.*;
import static by.epam.finalproject.controller.PathPage.*;
import static by.epam.finalproject.controller.PropertiesKey.*;

public class RegistrationCommand implements Command {
    private static final UserService service = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Map<String,String> mapData = new HashMap<>();
        mapData.put(USER_FIRST_NAME, request.getParameter(USER_FIRST_NAME));
        mapData.put(USER_LAST_NAME, request.getParameter(USER_LAST_NAME));
        mapData.put(LOGIN, request.getParameter(LOGIN));
        mapData.put(PASSWORD, request.getParameter(PASSWORD));
        mapData.put(USER_EMAIL, request.getParameter(USER_EMAIL));
        mapData.put(USER_PHONE_NUMBER, request.getParameter(USER_PHONE_NUMBER));
        mapData.put(USER_BIRTHDAY, request.getParameter(USER_BIRTHDAY));
        Router router = new Router();
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(USER);
            User.UserRole role = user != null && user.getRole() == User.UserRole.ADMIN ?
                    User.UserRole.ADMIN : User.UserRole.CLIENT;
            if (service.userRegistration(mapData, role)) {
                router.setRedirectType();
                if(role == User.UserRole.ADMIN){
                    String currentPage = (String) session.getAttribute(CURRENT_PAGE);
                    router.setCurrentPage(currentPage);
                }else {
                    router.setCurrentPage(SIGN_PAGE);
                }
            } else {
                for (String key : mapData.keySet()) {
                    String currentValue = mapData.get(key);
                    switch (currentValue) {
                        case INVALID_BIRTHDAY -> request.setAttribute(INVALID_BIRTHDAY, INVALID_BIRTHDAY_MESSAGE);
                        case INVALID_FIRST_NAME -> request.setAttribute(INVALID_FIRST_NAME, INVALID_FIRST_MESSAGE);
                        case INVALID_EMAIL -> request.setAttribute(INVALID_EMAIL, INVALID_EMAIL_MESSAGE);
                        case INVALID_LAST_NAME -> request.setAttribute(INVALID_LAST_NAME, INVALID_LAST_MESSAGE);
                        case INVALID_LOGIN -> request.setAttribute(INVALID_LOGIN, INVALID_LOGIN_MESSAGE);
                        case INVALID_PASSWORD -> request.setAttribute(INVALID_PASSWORD, INVALID_PASSWORD_MESSAGE);
                        case INVALID_PHONE_NUMBER -> request.setAttribute(INVALID_PHONE_NUMBER, INVALID_PHONE_NUMBER_MESSAGE);
                        case NOT_UNIQ_EMAIL -> request.setAttribute(INVALID_EMAIL, NOT_UNIQ_EMAIL_MESSAGE);
                        case NOT_UNIQ_LOGIN -> request.setAttribute(INVALID_LOGIN, NOT_UNIQ_LOGIN_MESSAGE);
                        case NOT_UNIQ_PHONE -> request.setAttribute(INVALID_PHONE_NUMBER, NOT_UNIQ_PHONE_MESSAGE);
                    }
                }
                router.setCurrentPage(REGISTRATION_PAGE);
            }
        } catch (ServiceException e) {
            throw new CommandException("Error while registration command", e);
        }
        return router;
    }
}
