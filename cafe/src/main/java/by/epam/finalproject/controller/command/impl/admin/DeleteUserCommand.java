package by.epam.finalproject.controller.command.impl.admin;

import by.epam.finalproject.controller.Router;
import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.exception.CommandException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.service.UserService;
import by.epam.finalproject.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.epam.finalproject.controller.Parameter.CURRENT_PAGE;
import static by.epam.finalproject.controller.Parameter.USER_ID;

public class DeleteUserCommand implements Command {
    private static final UserService service = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        long userId = Long.parseLong(request.getParameter(USER_ID));
        try {
            Router router = new Router();
            service.deleteUser(userId);
            HttpSession session = request.getSession();
            String page = (String) session.getAttribute(CURRENT_PAGE);
            router.setCurrentPage(page);
            router.setRedirectType();
            return router;
        } catch (ServiceException e) {
            throw new CommandException("Exception in a DeleteUserCommand class ", e);
        }
    }
}
