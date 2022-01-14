package by.epam.finalproject.controller.command.impl.admin;

import by.epam.finalproject.controller.Router;
import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.exception.CommandException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.entity.Order;
import by.epam.finalproject.model.service.OrderService;
import by.epam.finalproject.model.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.epam.finalproject.controller.Parameter.*;
import static by.epam.finalproject.controller.PathPage.ERROR_500;

public class ChangeOrderStateCommand implements Command {
    private final OrderService service = OrderServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(CURRENT_PAGE);
        router.setCurrentPage(currentPage);
        String state = request.getParameter(STATE);
        Order.OrderState orderState = Order.OrderState.valueOf(state);
        long id = Long.parseLong(request.getParameter(ORDER_ID));
        try {
            if(!service.changeOrderStateById(id, orderState)){
                router.setCurrentPage(ERROR_500);
                return router;
            }
            router.setRedirectType();
        } catch (ServiceException e) {
            throw new CommandException("Exception in a ChangeOrderStateCommand class. ", e);
        }
        return router;
    }
}
