package by.epam.finalproject.controller.command.impl.client;

import by.epam.finalproject.controller.Router;
import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.exception.CommandException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.entity.User;
import by.epam.finalproject.model.entity.UserDiscount;
import by.epam.finalproject.model.service.OrderService;
import by.epam.finalproject.model.service.UserDiscountService;
import by.epam.finalproject.model.service.impl.OrderServiceImpl;
import by.epam.finalproject.model.service.impl.UserDiscountServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Optional;

import static by.epam.finalproject.controller.SessionAttribute.USER;
import static by.epam.finalproject.controller.Parameter.USER_DISCOUNT;
import static by.epam.finalproject.controller.Parameter.USER_ORDERS_FOR_THE_YEAR;

import static by.epam.finalproject.controller.PathPage.DISCOUNT_PAGE;

/**
 * The type Calculate user discount command.
 */
public class CalculateUserDiscountCommand implements Command {
    private final UserDiscountService discountService = UserDiscountServiceImpl.getInstance();
    private final OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        try {
            Optional<UserDiscount> userDiscount = discountService.findDiscountById(user.getDiscountId());
            userDiscount.ifPresent(discount -> request.setAttribute(USER_DISCOUNT, discount.getDiscount()));

            int numberUserOrders = orderService.calculateOrdersNumberPerYear(user.getUserId());
            request.setAttribute(USER_ORDERS_FOR_THE_YEAR, numberUserOrders);
            router.setCurrentPage(DISCOUNT_PAGE);
        } catch (ServiceException e) {
            throw new CommandException("Exception in a CalculateUserDiscountCommand class. ", e);
        }
        return router;
    }
}
