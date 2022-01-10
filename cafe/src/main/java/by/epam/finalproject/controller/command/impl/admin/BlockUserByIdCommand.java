package by.epam.finalproject.controller.command.impl.admin;

import by.epam.finalproject.controller.Router;
import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.exception.CommandException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.entity.User;
import by.epam.finalproject.model.service.UserService;
import by.epam.finalproject.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.epam.finalproject.controller.Parameter.CURRENT_PAGE;
import static by.epam.finalproject.controller.Parameter.USER_ID;
import static by.epam.finalproject.controller.PathPage.ERROR_500;

public class BlockUserByIdCommand implements Command {
    private final UserService service = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        router.setRedirectType();
        long id = Long.parseLong(request.getParameter(USER_ID));
        try {
            if (!service.changeUserStateById(User.UserState.BLOCKED, id)) {
                router.setCurrentPage(ERROR_500);
                return router;
            }
            HttpSession session = request.getSession();
            String currentPage = (String) session.getAttribute(CURRENT_PAGE);
            router.setCurrentPage(currentPage);
        } catch (ServiceException e) {
            throw new CommandException("Exception in a BlockUserByIdCommand class ", e);
        }
        return router;
    }
}