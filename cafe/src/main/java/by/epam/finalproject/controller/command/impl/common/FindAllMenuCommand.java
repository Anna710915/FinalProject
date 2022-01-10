package by.epam.finalproject.controller.command.impl.common;

import by.epam.finalproject.controller.Router;
import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.exception.CommandException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.entity.Menu;
import by.epam.finalproject.model.service.MenuService;
import by.epam.finalproject.model.service.impl.MenuServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.epam.finalproject.controller.Parameter.MENU_LIST;
import static by.epam.finalproject.controller.PathPage.MENU_PAGE;

public class FindAllMenuCommand implements Command {
    private static final MenuService menuService = MenuServiceImpl.getInstance();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        try {
            List<Menu> menuList = menuService.findAllMenu();
            request.setAttribute(MENU_LIST, menuList);
            router.setCurrentPage(MENU_PAGE);
        } catch (ServiceException e) {
            throw new CommandException("Exception in a FindAllMenuCommand class", e);
        }
        return router;
    }
}
