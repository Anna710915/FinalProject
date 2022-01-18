package by.epam.finalproject.controller.command.impl.admin;

import by.epam.finalproject.controller.Router;
import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.exception.CommandException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.entity.Section;
import by.epam.finalproject.model.service.SectionService;
import by.epam.finalproject.model.service.impl.SectionServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import static by.epam.finalproject.controller.Parameter.CURRENT_PAGE;
import static by.epam.finalproject.controller.Parameter.PRODUCT_SECTION;
import static by.epam.finalproject.controller.Parameter.INVALID_DELETE_PRODUCT_SECTION;
import static by.epam.finalproject.controller.Parameter.SECTION_LIST;
import static by.epam.finalproject.controller.PropertiesKey.INVALID_PRODUCT_SECTION_MESSAGE;

/**
 * The type Delete section command.
 */
public class DeleteSectionCommand implements Command {
    private final SectionService service = SectionServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        String currentPage = (String) session.getAttribute(CURRENT_PAGE);
        router.setCurrentPage(currentPage);

        if(request.getParameter(PRODUCT_SECTION) == null){
            request.setAttribute(INVALID_DELETE_PRODUCT_SECTION, INVALID_PRODUCT_SECTION_MESSAGE);
            return router;
        }

        try {
            long sectionId = Long.parseLong(request.getParameter(PRODUCT_SECTION));
            service.deleteSectionById(sectionId);
            List<Section> listSection = service.findAllSections();
            context.setAttribute(SECTION_LIST, listSection);
        } catch (ServiceException | NumberFormatException e) {
            throw new CommandException("Exception in a DeleteSectionCommand class. ", e);
        }
        return router;
    }
}
