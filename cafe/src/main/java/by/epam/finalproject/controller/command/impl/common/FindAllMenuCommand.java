package by.epam.finalproject.controller.command.impl.common;

import by.epam.finalproject.controller.Router;
import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.exception.CommandException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.entity.Menu;
import by.epam.finalproject.model.service.MenuService;
import by.epam.finalproject.model.service.PaginationService;
import by.epam.finalproject.model.service.impl.MenuServiceImpl;
import by.epam.finalproject.util.URLUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static by.epam.finalproject.controller.Parameter.*;
import static by.epam.finalproject.controller.PathPage.MENU_PAGE;

public class FindAllMenuCommand implements Command {
    private static final int PAGE_SIZE = 4;
    private final MenuService menuService = MenuServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        try {
            String currentPageParameter = request.getParameter(PAGINATION_PAGE);
            int currentPage = 1;
            if(currentPageParameter != null){
                currentPage = Integer.parseInt(currentPageParameter);
            }

            int offset = PaginationService.offset(PAGE_SIZE, currentPage);
            List<Menu> menuSublist = menuService.findMenuSublist(PAGE_SIZE, offset);
            int totalRecords = menuService.readRowCount();
            int pages = PaginationService.pages(totalRecords, PAGE_SIZE);
            int lastPage = PaginationService.lastPage(pages, PAGE_SIZE, totalRecords);

            request.setAttribute(MENU_LIST, menuSublist);
            request.setAttribute(PAGINATION_PAGE, currentPage);
            request.setAttribute(PAGINATION_LAST_PAGE, lastPage);
            request.setAttribute(URL, URLUtil.createURL(Map.of(COMMAND, request.getParameter(COMMAND))));
            router.setCurrentPage(MENU_PAGE);
        } catch (ServiceException e) {
            throw new CommandException("Exception in a FindAllMenuCommand class", e);
        }
        return router;
    }
}
