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
import java.util.Optional;

import static by.epam.finalproject.controller.Parameter.*;
import static by.epam.finalproject.controller.Parameter.USER_BIRTHDAY;
import static by.epam.finalproject.controller.PropertiesKey.*;
import static by.epam.finalproject.controller.PropertiesKey.NOT_UNIQ_PHONE_MESSAGE;

public class UpdateUserProfileCommand implements Command {
    private static final UserService service = UserServiceImpl.getInstance();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Map<String,String> updateProfileData = new HashMap<>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        updateProfileData.put(USER_FIRST_NAME, request.getParameter(USER_FIRST_NAME));
        updateProfileData.put(USER_LAST_NAME, request.getParameter(USER_LAST_NAME));
        updateProfileData.put(USER_EMAIL, request.getParameter(USER_EMAIL));
        updateProfileData.put(USER_PHONE_NUMBER, request.getParameter(USER_PHONE_NUMBER));
        updateProfileData.put(USER_BIRTHDAY, request.getParameter(USER_BIRTHDAY));
        Router router = new Router();
        try {
            Optional<User> optionalUser = service.updateUserProfile(user, updateProfileData);
            String currentPage = (String) session.getAttribute(CURRENT_PAGE);
            router.setCurrentPage(currentPage);
            if(optionalUser.isPresent()){
                session.setAttribute(USER, optionalUser.get());
                router.setRedirectType();
            }else{
                for (String key : updateProfileData.keySet()) {
                    String currentValue = updateProfileData.get(key);
                    switch (currentValue) {
                        case INVALID_BIRTHDAY -> request.setAttribute(INVALID_BIRTHDAY, INVALID_BIRTHDAY_MESSAGE);
                        case INVALID_FIRST_NAME -> request.setAttribute(INVALID_FIRST_NAME, INVALID_FIRST_MESSAGE);
                        case INVALID_EMAIL -> request.setAttribute(INVALID_EMAIL, INVALID_EMAIL_MESSAGE);
                        case INVALID_LAST_NAME -> request.setAttribute(INVALID_LAST_NAME, INVALID_LAST_MESSAGE);
                        case INVALID_PHONE_NUMBER -> request.setAttribute(INVALID_PHONE_NUMBER, INVALID_PHONE_NUMBER_MESSAGE);
                        case NOT_UNIQ_EMAIL -> request.setAttribute(INVALID_EMAIL, NOT_UNIQ_EMAIL_MESSAGE);
                        case NOT_UNIQ_PHONE -> request.setAttribute(INVALID_PHONE_NUMBER, NOT_UNIQ_PHONE_MESSAGE);
                    }
                }
            }
        } catch (ServiceException e) {
            throw new CommandException("Exception in a UpdateUserProfileCommand class", e);
        }
        return router;
    }
}
