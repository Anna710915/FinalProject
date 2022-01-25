package by.epam.finalproject.controller.command.impl.admin;

import by.epam.finalproject.controller.Router;
import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.exception.CommandException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.entity.Menu;
import by.epam.finalproject.model.service.MenuService;
import by.epam.finalproject.model.service.impl.MenuServiceImpl;

import javax.servlet.http.HttpServletRequest;

import java.util.Optional;

import static by.epam.finalproject.controller.Parameter.PRODUCT_ID;
import static by.epam.finalproject.controller.Parameter.PRODUCT_MENU;
import static by.epam.finalproject.controller.PathPage.ERROR_500;
import static by.epam.finalproject.controller.PathPage.UPDATE_PRODUCT_PAGE;

/**
 * The type Go to update product page command.
 */
public class GoToUpdateProductPageCommand implements Command {
    private final MenuService service = MenuServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        try {
            long menuId = Long.parseLong(request.getParameter(PRODUCT_ID));
            Optional<Menu> menu = service.findProductById(menuId);
            if(menu.isEmpty()){
                router.setRedirectType();
                router.setCurrentPage(ERROR_500);
                return router;
            }
            request.setAttribute(PRODUCT_MENU, menu.get());
            router.setCurrentPage(UPDATE_PRODUCT_PAGE);
        } catch (ServiceException | NumberFormatException e) {
            throw new CommandException("Exception in a GoToUpdateProductPageCommand class", e);
        }
        return router;
    }
}
