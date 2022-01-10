package by.epam.finalproject.controller.command.impl.admin;

import by.epam.finalproject.controller.Router;
import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.exception.CommandException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.service.MenuService;
import by.epam.finalproject.model.service.impl.MenuServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.epam.finalproject.controller.Parameter.CURRENT_PAGE;
import static by.epam.finalproject.controller.Parameter.PRODUCT_ID;
import static by.epam.finalproject.controller.PathPage.ERROR_500;

public class DeleteProductCommand implements Command {
    private final MenuService service = MenuServiceImpl.getInstance();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        long id = Long.parseLong(request.getParameter(PRODUCT_ID));
        try {
            router.setRedirectType();
            if(!service.deleteProductById(id)){
                router.setCurrentPage(ERROR_500);
                return router;
            }
            HttpSession session = request.getSession();
            String currentPage = (String) session.getAttribute(CURRENT_PAGE);
            router.setCurrentPage(currentPage);
        } catch (ServiceException e) {
            throw new CommandException("Exception in a DeleteProductCommand class ", e);
        }
        return router;
    }
}
