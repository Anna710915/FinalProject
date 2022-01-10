package by.epam.finalproject.controller.command.impl.admin;

import by.epam.finalproject.controller.Router;
import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.exception.CommandException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.entity.Order;
import by.epam.finalproject.model.entity.User;
import by.epam.finalproject.model.service.OrderService;
import by.epam.finalproject.model.service.UserService;
import by.epam.finalproject.model.service.impl.OrderServiceImpl;
import by.epam.finalproject.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.epam.finalproject.controller.Parameter.ORDER_LIST;
import static by.epam.finalproject.controller.Parameter.ORDER_MAP;
import static by.epam.finalproject.controller.PathPage.ORDERS_PAGE;

public class FindAllOrdersCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final OrderService orderService = OrderServiceImpl.getInstance();
    private final UserService userService = UserServiceImpl.getInstance();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        router.setCurrentPage(ORDERS_PAGE);
        try {
            Map<Order, User> orders = new HashMap<>();
            List<Order> orderList = orderService.findAllOrders();
            for(Order order: orderList){
                Optional<User> optionalUser = userService.findUserByOrder(order.getOrderId());
                if(optionalUser.isPresent()){
                    User user = optionalUser.get();
                    orders.put(order, user);
                }else {
                    logger.log(Level.INFO, "The user doesn't exist. Order ID is " + order.getOrderId());
                }
            }
            request.setAttribute(ORDER_MAP, orders);
        } catch (ServiceException e) {
            throw new CommandException("Exception in a FindAllOrdersCommand class.", e);
        }
        return router;
    }
}
