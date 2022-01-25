package by.epam.finalproject.controller.command.impl.admin;

import by.epam.finalproject.controller.Router;
import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.exception.CommandException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.service.MenuService;
import by.epam.finalproject.model.service.impl.MenuServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.epam.finalproject.controller.SessionAttribute.CURRENT_PAGE;
import static by.epam.finalproject.controller.Parameter.PRODUCT_ID;

/**
 * The type Restore menu product command.
 */
public class RestoreMenuProductCommand implements Command {
    private final MenuService menuService = MenuServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(CURRENT_PAGE);
        router.setRedirectType();
        router.setCurrentPage(currentPage);
        try {
            long menuId = Long.parseLong(request.getParameter(PRODUCT_ID));
            menuService.restoreMenuProductById(menuId);
        } catch (ServiceException | NumberFormatException e) {
            throw new CommandException("Exception in a RestoreMenuProductCommand class ", e);
        }
        return router;
    }
}
