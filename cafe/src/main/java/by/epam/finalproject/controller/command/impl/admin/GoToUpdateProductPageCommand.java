package by.epam.finalproject.controller.command.impl.admin;

import by.epam.finalproject.controller.Router;
import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.exception.CommandException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.entity.Menu;
import by.epam.finalproject.model.service.MenuService;
import by.epam.finalproject.model.service.impl.MenuServiceImpl;

import javax.servlet.http.HttpServletRequest;

import static by.epam.finalproject.controller.Parameter.PRODUCT_ID;
import static by.epam.finalproject.controller.Parameter.PRODUCT_MENU;
import static by.epam.finalproject.controller.PathPage.ERROR_500;
import static by.epam.finalproject.controller.PathPage.UPDATE_PRODUCT_PAGE;

public class GoToUpdateProductPageCommand implements Command {
    private final MenuService service = MenuServiceImpl.getInstance();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        long menuId = Long.parseLong(request.getParameter(PRODUCT_ID));

        try {
            Menu menu = service.findProductById(menuId);
            if(menu == null){
                router.setRedirectType();
                router.setCurrentPage(ERROR_500);
                return router;
            }
            request.setAttribute(PRODUCT_MENU, menu);
            router.setCurrentPage(UPDATE_PRODUCT_PAGE);
        } catch (ServiceException e) {
            throw new CommandException("Exception in a GoToUpdateProductPageCommand class", e);
        }
        return router;
    }
}
