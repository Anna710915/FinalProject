package by.epam.finalproject.controller.command.impl.common;

import by.epam.finalproject.controller.Router;
import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.exception.CommandException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.entity.Menu;
import by.epam.finalproject.model.entity.User;
import by.epam.finalproject.model.service.UserService;
import by.epam.finalproject.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Optional;

import static by.epam.finalproject.controller.Parameter.*;
import static by.epam.finalproject.controller.PathPage.ADMIN_PAGE;
import static by.epam.finalproject.controller.PathPage.CLIENT_PAGE;
import static by.epam.finalproject.controller.PathPage.SIGN_PAGE;

import static by.epam.finalproject.controller.PropertiesKey.ERROR_INCORRECT_LOGIN_OR_PASSWORD_MESSAGE;
import static by.epam.finalproject.controller.PropertiesKey.USER_BLOCKED_MESSAGE;

public class SignInCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Router router = new Router();
        String login = request.getParameter(LOGIN);
        String pass = request.getParameter(PASSWORD);
        logger.log(Level.INFO,"login and pass" + login + pass);
        try {
            Optional<User> optionalUser = userService.signIn(login, pass);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                logger.log(Level.INFO,"Sign in" + user.getRole());
                switch (user.getRole()){
                    case ADMIN -> {
                        session.setAttribute(USER,user);
                        router.setCurrentPage(ADMIN_PAGE);
                    }
                    case CLIENT -> {
                        if(user.getState() == User.UserState.BLOCKED){
                            request.setAttribute(USER_STATUS_BLOCKED,USER_BLOCKED_MESSAGE);
                            router.setCurrentPage(SIGN_PAGE);
                        }else {
                            logger.log(Level.INFO,"Client page");
                            session.setAttribute(USER,user);
                            session.setAttribute(CART, new HashMap<Menu, Integer>());
                            router.setCurrentPage(CLIENT_PAGE);
                        }
                    }
                }
            } else {
                logger.log(Level.DEBUG,"SignInCommand");
                request.setAttribute(ERROR_LOG_OR_PASS, ERROR_INCORRECT_LOGIN_OR_PASSWORD_MESSAGE);
                router.setCurrentPage(SIGN_PAGE);
            }
        } catch (ServiceException e) {
            throw new CommandException("Error during sign in", e);
        }
        logger.log(Level.INFO,"SignInCommand");
        return router;
    }
}
