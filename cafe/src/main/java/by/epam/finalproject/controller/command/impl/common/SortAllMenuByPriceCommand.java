package by.epam.finalproject.controller.command.impl.common;

import by.epam.finalproject.controller.Router;
import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.exception.CommandException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.entity.Menu;
import by.epam.finalproject.model.service.MenuService;
import by.epam.finalproject.model.service.PaginationService;
import by.epam.finalproject.model.service.impl.MenuServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.epam.finalproject.controller.Parameter.PAGINATION_PAGE;
import static by.epam.finalproject.controller.Parameter.SECTION_ID;
import static by.epam.finalproject.controller.Parameter.URL;
import static by.epam.finalproject.controller.Parameter.SIGN;
import static by.epam.finalproject.controller.Parameter.EQUAL;
import static by.epam.finalproject.controller.Parameter.MENU_LIST;
import static by.epam.finalproject.controller.Parameter.PAGINATION_LAST_PAGE;
import static by.epam.finalproject.controller.Parameter.COMMAND;

import static by.epam.finalproject.controller.PathPage.MENU_PAGE;

/**
 * The type Sort all menu by price command.
 */
public class SortAllMenuByPriceCommand implements Command {
    private static final int PAGE_SIZE = 4;
    private final MenuService menuService = MenuServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String currentPageParameter = request.getParameter(PAGINATION_PAGE);
        String sectionId = request.getParameter(SECTION_ID);
        int currentPage = 1;
        if(currentPageParameter != null){
            currentPage = Integer.parseInt(currentPageParameter);
        }

        int offset = PaginationService.offset(PAGE_SIZE, currentPage);
        int totalRecords;
        try {
            List<Menu> menuSublist;
            if(sectionId == null) {
                menuSublist = menuService.sortAllMenuByPrice(PAGE_SIZE, offset);
                request.setAttribute(URL, Command.createURL(request, request.getParameter(COMMAND)));
                totalRecords = menuService.readRowCount();
            }else{
                long id = Long.parseLong(sectionId);
                menuSublist = menuService.sortSectionMenuByPrice(PAGE_SIZE, offset, id);
                StringBuilder urlBuilder = new StringBuilder(Command.createURL(request, request.getParameter(COMMAND)));
                urlBuilder.append(SIGN).append(SECTION_ID).append(EQUAL).append(sectionId);
                request.setAttribute(URL, urlBuilder);
                totalRecords = menuService.readRowCountBySection(id);
            }

            int pages = PaginationService.pages(totalRecords, PAGE_SIZE);
            int lastPage = PaginationService.lastPage(pages, PAGE_SIZE, totalRecords);
            request.setAttribute(MENU_LIST, menuSublist);
            request.setAttribute(PAGINATION_PAGE, currentPage);
            request.setAttribute(PAGINATION_LAST_PAGE, lastPage);
            router.setCurrentPage(MENU_PAGE);
        } catch (ServiceException | NumberFormatException e) {
            throw new CommandException("Exception in a SortAllMenuByPriceCommand class. ", e);
        }
        return router;
    }
}
