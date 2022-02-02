package by.epam.finalproject.controller.command.impl.client;

import by.epam.finalproject.controller.Router;
import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.exception.CommandException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.entity.Menu;
import by.epam.finalproject.model.service.MenuService;
import by.epam.finalproject.model.service.impl.MenuServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import static by.epam.finalproject.controller.SessionAttribute.CURRENT_PAGE;
import static by.epam.finalproject.controller.Parameter.PRODUCT_NUMBER;
import static by.epam.finalproject.controller.Parameter.SELECTED;
import static by.epam.finalproject.controller.SessionAttribute.CART;

/**
 * The type Add product to cart command.
 */
public class AddProductToCartCommand implements Command {
    private final MenuService service = MenuServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(CURRENT_PAGE);
        Map<Menu, Integer> productMap = (HashMap<Menu, Integer>) session.getAttribute(CART);
        int productNumber = Integer.parseInt(request.getParameter(PRODUCT_NUMBER));
        Router router = new Router();
        try {
            if(productNumber >= 1) {
                long id = Long.parseLong(request.getParameter(SELECTED));
                service.addProductToBasket(productMap, id, productNumber);
                session.setAttribute(CART, productMap);
            }
            router.setRedirectType();
            router.setCurrentPage(currentPage);
        } catch (ServiceException | NumberFormatException e) {
            throw new CommandException("Exception in a AddProductToCartCommand class ", e);
        }
        return router;
    }
}
