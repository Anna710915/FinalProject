package by.epam.finalproject.controller.command.impl.admin;

import by.epam.finalproject.controller.Router;
import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.exception.CommandException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.entity.CompleteOrder;
import by.epam.finalproject.model.service.OrderService;
import by.epam.finalproject.model.service.impl.OrderServiceImpl;
import javax.servlet.http.HttpServletRequest;

import java.util.List;
import static by.epam.finalproject.controller.PathPage.ORDERS_PAGE;
import static by.epam.finalproject.controller.Parameter.ORDER_LIST;

/**
 * The type Find all orders command.
 */
public class FindAllOrdersCommand implements Command {
    private final OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        router.setCurrentPage(ORDERS_PAGE);
        try {
            List<CompleteOrder> orderList = orderService.findAllOrders();
            request.setAttribute(ORDER_LIST, orderList);
        } catch (ServiceException e) {
            throw new CommandException("Exception in a FindAllOrdersCommand class.", e);
        }
        return router;
    }
}
