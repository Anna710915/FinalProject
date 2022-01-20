package by.epam.finalproject.controller.command.impl.admin;

import by.epam.finalproject.controller.Router;
import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.exception.CommandException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.service.MenuService;
import by.epam.finalproject.model.service.impl.MenuServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.epam.finalproject.controller.Parameter.*;

/**
 * The type Delete product command.
 */
public class DeleteProductCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final MenuService service = MenuServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        try {
            long id = Long.parseLong(request.getParameter(PRODUCT_ID));
            service.deleteProductById(id);
            HttpSession session = request.getSession();
            String currentPage = (String) session.getAttribute(CURRENT_PAGE);
            logger.log(Level.INFO, "Current page after delete product - " + currentPage);
            session.setAttribute(CURRENT_PAGE, currentPage);
            router.setCurrentPage(currentPage);
        } catch (ServiceException | NumberFormatException e) {
            throw new CommandException("Exception in a DeleteProductCommand class ", e);
        }
        return router;
    }
}
