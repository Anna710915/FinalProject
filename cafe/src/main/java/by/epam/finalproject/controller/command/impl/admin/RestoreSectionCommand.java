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

import static by.epam.finalproject.controller.Parameter.*;

/**
 * The type Restore section command.
 */
public class RestoreSectionCommand implements Command {
    private final SectionService sectionService = SectionServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        router.setRedirectType();
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(CURRENT_PAGE);
        router.setCurrentPage(currentPage);
        try {
            long sectionId = Long.parseLong(request.getParameter(SECTION_ID));
            if(sectionService.restoreSectionById(sectionId)) {
                ServletContext context = request.getServletContext();
                List<Section> sectionList = sectionService.findAllSections();
                context.setAttribute(SECTION_LIST, sectionList);
            }
        } catch (ServiceException e) {
            throw new CommandException("Exception in a RestoreSectionCommand class ", e);
        }
        return router;
    }
}
