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

import static by.epam.finalproject.controller.Parameter.*;

public class DeleteProductInBasketCommand implements Command {
    private final MenuService service = MenuServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(CURRENT_PAGE);
        long id = Long.parseLong(request.getParameter(PRODUCT_ID));
        Map<Menu, Integer> mapMenu = (HashMap<Menu, Integer>) session.getAttribute(CART);
        try {
            if(service.deleteProductFromBasket(mapMenu, id)) {
                session.setAttribute(CART, mapMenu);
            }
        } catch (ServiceException e) {
            throw new CommandException("Exception in a DeleteProductInBasketCommand class ", e);
        }
        router.setCurrentPage(currentPage);
        return router;
    }
}
