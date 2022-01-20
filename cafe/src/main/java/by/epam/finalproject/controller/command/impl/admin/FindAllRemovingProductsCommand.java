package by.epam.finalproject.controller.command.impl.admin;

import by.epam.finalproject.controller.Router;
import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.exception.CommandException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.entity.Menu;
import by.epam.finalproject.model.service.MenuService;
import by.epam.finalproject.model.service.impl.MenuServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static by.epam.finalproject.controller.Parameter.MENU_LIST;
import static by.epam.finalproject.controller.Parameter.RESTORE_MENU;
import static by.epam.finalproject.controller.PathPage.RESTORE_PAGE;

/**
 * The type Find all removing products command.
 */
public class FindAllRemovingProductsCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final MenuService menuService = MenuServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        router.setCurrentPage(RESTORE_PAGE);
        try {
            List<Menu> menuList = menuService.findAllRemovingMenu();
            logger.log(Level.INFO, "Removing products: " + menuList);
            request.setAttribute(MENU_LIST, menuList);
            request.setAttribute(RESTORE_MENU, true);
        } catch (ServiceException e) {
            throw new CommandException("Exception in a FindAllRemovingProductsCommand class ", e);
        }
        return router;
    }
}
