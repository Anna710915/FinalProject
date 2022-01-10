package by.epam.finalproject.controller.command.impl.admin;

import by.epam.finalproject.controller.Router;
import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.exception.CommandException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.entity.User;
import by.epam.finalproject.model.service.UserService;
import by.epam.finalproject.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.epam.finalproject.controller.Parameter.LIST_USER;
import static by.epam.finalproject.controller.PathPage.USERS_PAGE;

public class FindAllUsersCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        try {
            List<User> listUsers = userService.findAllUsers();
            request.setAttribute(LIST_USER,listUsers);
            router.setCurrentPage(USERS_PAGE);
            logger.log(Level.INFO,USERS_PAGE);
        } catch (ServiceException e) {
            throw new CommandException("Exception in a FindAllUsersCommand class ", e);
        }
        return router;
    }
}
