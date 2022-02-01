package by.epam.finalproject.controller.command.impl.common;

import by.epam.finalproject.controller.Router;
import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.exception.CommandException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.entity.Menu;
import by.epam.finalproject.model.service.MenuService;
import by.epam.finalproject.model.service.PaginationService;
import by.epam.finalproject.model.service.impl.MenuServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.epam.finalproject.controller.Parameter.*;
import static by.epam.finalproject.controller.PathPage.MENU_PAGE;

/**
 * The type Sort all menu by popularity command.
 */
public class SortAllMenuByPopularityCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
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
            StringBuilder builderUrl;
            if(sectionId == null) {
                menuSublist = menuService.findSortedMenuSubListByPopularity(PAGE_SIZE, offset);
                logger.log(Level.INFO, menuSublist);
                if(menuSublist.isEmpty() && currentPage > 1){
                    currentPage--;
                    offset = PaginationService.offset(PAGE_SIZE, currentPage);
                    menuSublist = menuService.findSortedMenuSubListByPopularity(PAGE_SIZE, offset);
                    logger.log(Level.INFO, menuSublist);
                }
                totalRecords = menuService.readRowCount();
                builderUrl = new StringBuilder(Command.createURL(request, request.getParameter(COMMAND)));
            }else{
                long id = Long.parseLong(sectionId);
                logger.log(Level.INFO, "id = " + id);
                menuSublist = menuService.findSortedMenuSectionSubListByPopularity(PAGE_SIZE, offset, id);
                if(menuSublist.isEmpty() && currentPage > 1){
                    currentPage--;
                    offset = PaginationService.offset(PAGE_SIZE, currentPage);
                    menuSublist = menuService.findSortedMenuSectionSubListByPopularity(PAGE_SIZE, offset, id);
                }
                totalRecords = menuService.readRowCountBySection(id);
                builderUrl = new StringBuilder(Command.createURL(request, request.getParameter(COMMAND)));
                builderUrl.append(SIGN).append(SECTION_ID).append(EQUAL).append(sectionId);
            }

            int pages = PaginationService.pages(totalRecords, PAGE_SIZE);
            int lastPage = PaginationService.lastPage(pages, PAGE_SIZE, totalRecords);
            request.setAttribute(MENU_LIST, menuSublist);
            request.setAttribute(PAGINATION_PAGE, currentPage);
            request.setAttribute(PAGINATION_LAST_PAGE, lastPage);
            request.setAttribute(URL, builderUrl.toString());
            router.setCurrentPage(MENU_PAGE);
        } catch (ServiceException | NumberFormatException e) {
            throw new CommandException("Exception in a SortAllMenuByPriceCommand class. ", e);
        }
        return router;
    }
}
