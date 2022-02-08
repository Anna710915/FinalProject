package by.epam.finalproject.controller.command.impl.client;

import by.epam.finalproject.controller.Router;
import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.exception.CommandException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.entity.Menu;
import by.epam.finalproject.model.entity.User;
import by.epam.finalproject.model.service.OrderService;
import by.epam.finalproject.model.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static by.epam.finalproject.controller.Parameter.*;
import static by.epam.finalproject.controller.SessionAttribute.USER;
import static by.epam.finalproject.controller.SessionAttribute.CART;
import static by.epam.finalproject.controller.SessionAttribute.CURRENT_PAGE;

import static by.epam.finalproject.controller.PathPage.SUCCESS_PAGE;

import static by.epam.finalproject.controller.PropertiesKey.INVALID_ORDER_ADDRESS_MESSAGE;
import static by.epam.finalproject.controller.PropertiesKey.INVALID_ORDER_PAYMENT_MESSAGE;
import static by.epam.finalproject.controller.PropertiesKey.INVALID_ORDER_COMMENT_MESSAGE;

/**
 * The type Create order command.
 */
public class CreateOrderCommand implements Command {
    private final OrderService service = OrderServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        Map<Menu, Integer> orderProduct = (HashMap<Menu, Integer>) session.getAttribute(CART);
        Map<String, String> orderInfo = new HashMap<>();

        orderInfo.put(ADDRESS,request.getParameter(ADDRESS));
        orderInfo.put(PRODUCT_PAYMENT, request.getParameter(PRODUCT_PAYMENT));
        orderInfo.put(USER_COMMENT, request.getParameter(USER_COMMENT));
        try {
            double price = Double.parseDouble(request.getParameter(TOTAL_PRICE));
            BigDecimal totalCost = BigDecimal.valueOf(price);
            if(service.createOrder(orderProduct, orderInfo, user, totalCost)){
                router.setCurrentPage(SUCCESS_PAGE);
                router.setRedirectType();
                orderProduct.clear();
                session.setAttribute(CART, orderProduct);
                return router;
            }else {
                String currentPage = (String) session.getAttribute(CURRENT_PAGE);
                router.setCurrentPage(currentPage);
                for(String key: orderInfo.keySet()){
                    String value = orderInfo.get(key);
                    switch (value){
                        case INVALID_ORDER_ADDRESS -> request.setAttribute(INVALID_ORDER_ADDRESS, INVALID_ORDER_ADDRESS_MESSAGE);
                        case INVALID_ORDER_PAYMENT -> request.setAttribute(INVALID_ORDER_PAYMENT, INVALID_ORDER_PAYMENT_MESSAGE);
                        case INVALID_ORDER_COMMENT -> request.setAttribute(INVALID_ORDER_COMMENT, INVALID_ORDER_COMMENT_MESSAGE);
                    }
                }
            }
        } catch (ServiceException | NumberFormatException e) {
            throw new CommandException("Exception in a CreateOrderCommand class. ", e);
        }
        return router;
    }
}
