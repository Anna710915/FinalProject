package by.epam.finalproject.controller.command.impl.client;

import by.epam.finalproject.controller.Router;
import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.exception.CommandException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.entity.Menu;
import by.epam.finalproject.model.entity.User;
import by.epam.finalproject.model.entity.UserDiscount;
import by.epam.finalproject.model.service.CalculateService;
import by.epam.finalproject.model.service.UserDiscountService;
import by.epam.finalproject.model.service.impl.UserDiscountServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static by.epam.finalproject.controller.Parameter.*;
import static by.epam.finalproject.controller.PathPage.BASKET_PAGE;

public class GoToBasketPageCommand implements Command {
    private final UserDiscountService service = UserDiscountServiceImpl.getInstance();
    private final CalculateService calculateService = CalculateService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        Map<Menu, Integer> productMap = (HashMap<Menu, Integer>) session.getAttribute(CART);
        User user = (User) session.getAttribute(USER);
        router.setCurrentPage(BASKET_PAGE);
        try {
            if(!productMap.isEmpty()) {
                UserDiscount userDiscount = service.findDiscountById(user.getDiscountId());
                double totalPrice = calculateService.calculateTotalPrice(userDiscount, productMap).doubleValue();
                request.setAttribute(TOTAL_PRICE, totalPrice);
            }
        } catch (ServiceException e) {
            throw new CommandException("Exception in a GoToBasketPageCommand class", e);
        }
        return router;
    }
}
