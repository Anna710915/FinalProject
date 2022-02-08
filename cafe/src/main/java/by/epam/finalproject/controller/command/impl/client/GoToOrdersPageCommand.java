package by.epam.finalproject.controller.command.impl.client;

import by.epam.finalproject.controller.Router;
import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.exception.CommandException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.entity.Order;
import by.epam.finalproject.model.entity.User;
import by.epam.finalproject.model.service.OrderService;
import by.epam.finalproject.model.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import static by.epam.finalproject.controller.Parameter.ORDER_LIST;
import static by.epam.finalproject.controller.SessionAttribute.USER;
import static by.epam.finalproject.controller.PathPage.ORDERS_PAGE;

/**
 * The type Go to orders page command.
 */
public class GoToOrdersPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final OrderService service = OrderServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        router.setCurrentPage(ORDERS_PAGE);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        try {
            logger.log(Level.INFO,"User: " + user);
            List<Order> listOrder = service.findAllUserOrders(user);
            logger.log(Level.INFO, "List order: " + listOrder);
            request.setAttribute(ORDER_LIST, listOrder);
        } catch (ServiceException e) {
            throw new CommandException("Exception in a GoToOrdersPageCommand class. ", e);
        }
        return router;
    }
}
