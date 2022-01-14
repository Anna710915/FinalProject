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

import java.util.Map;

import static by.epam.finalproject.controller.Parameter.*;
import static by.epam.finalproject.controller.PathPage.ERROR_500;

public class AddProductToCartCommand implements Command {
    private final MenuService service = MenuServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(CURRENT_PAGE);
        Map<Menu, Integer> productMap = (Map) session.getAttribute(CART);
        long id = Long.parseLong(request.getParameter(SELECTED));
        int productNumber = Integer.parseInt(request.getParameter(PRODUCT_NUMBER));
        Router router = new Router();
        try {
            if(!service.addProductToBasket(productMap, id, productNumber)){
                router.setCurrentPage(ERROR_500);
                return router;
            }
            router.setRedirectType();
            session.setAttribute(CART, productMap);
            router.setCurrentPage(currentPage);
        } catch (ServiceException e) {
            throw new CommandException("Exception in a AddProductToCartCommand class ", e);
        }
        return router;
    }
}
