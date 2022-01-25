package by.epam.finalproject.controller.command.impl.admin;

import by.epam.finalproject.controller.Router;
import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.exception.CommandException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.service.OrderService;
import by.epam.finalproject.model.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.epam.finalproject.controller.SessionAttribute.CURRENT_PAGE;

/**
 * The type Delete orders command.
 */
public class DeleteOrdersCommand implements Command {
    private final OrderService service = OrderServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(CURRENT_PAGE);
        router.setCurrentPage(currentPage);
        try {
            service.deleteOldOrders();
        } catch (ServiceException e) {
            throw new CommandException("Exception in a DeleteOrdersCommand class. ", e);
        }
        return router;
    }
}
