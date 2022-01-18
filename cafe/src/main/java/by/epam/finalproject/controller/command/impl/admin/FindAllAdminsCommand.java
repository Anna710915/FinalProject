package by.epam.finalproject.controller.command.impl.admin;

import by.epam.finalproject.controller.Router;
import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.exception.CommandException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.entity.User;
import by.epam.finalproject.model.service.UserService;
import by.epam.finalproject.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.epam.finalproject.controller.Parameter.LIST_USER;
import static by.epam.finalproject.controller.PathPage.USERS_PAGE;

/**
 * The type Find all admins command.
 */
public class FindAllAdminsCommand implements Command {
    private final UserService service = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        try {
            router.setCurrentPage(USERS_PAGE);
            List<User> listAdmin = service.findAllAdmins();
            request.setAttribute(LIST_USER, listAdmin);
        } catch (ServiceException e) {
            throw new CommandException("Exception in a FindAllAdminsCommand class. ", e);
        }
        return router;
    }
}
