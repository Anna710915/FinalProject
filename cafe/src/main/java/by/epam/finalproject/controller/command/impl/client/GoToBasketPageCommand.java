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
import java.util.Optional;

import static by.epam.finalproject.controller.SessionAttribute.USER;
import static by.epam.finalproject.controller.SessionAttribute.CART;
import static by.epam.finalproject.controller.Parameter.TOTAL_PRICE;

import static by.epam.finalproject.controller.PathPage.BASKET_PAGE;
import static by.epam.finalproject.controller.PathPage.ERROR_500;


/**
 * The type Go to basket page command.
 */
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
                Optional<UserDiscount> userDiscount = service.findDiscountById(user.getDiscountId());
                if(userDiscount.isPresent()) {
                    double totalPrice = calculateService.calculateTotalPrice(userDiscount.get(), productMap).doubleValue();
                    request.setAttribute(TOTAL_PRICE, totalPrice);
                }else{
                    router.setRedirectType();
                    router.setCurrentPage(ERROR_500);
                    return router;
                }
            }
        } catch (ServiceException e) {
            throw new CommandException("Exception in a GoToBasketPageCommand class", e);
        }
        return router;
    }
}
