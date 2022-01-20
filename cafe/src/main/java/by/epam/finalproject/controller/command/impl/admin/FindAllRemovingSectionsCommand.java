package by.epam.finalproject.controller.command.impl.admin;

import by.epam.finalproject.controller.Router;
import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.exception.CommandException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.entity.Section;
import by.epam.finalproject.model.service.SectionService;
import by.epam.finalproject.model.service.impl.SectionServiceImpl;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static by.epam.finalproject.controller.Parameter.RESTORE_SECTION;
import static by.epam.finalproject.controller.Parameter.SECTION_LIST;
import static by.epam.finalproject.controller.PathPage.RESTORE_PAGE;

/**
 * The type Find all removing sections command.
 */
public class FindAllRemovingSectionsCommand implements Command {
    private final SectionService sectionService = SectionServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        router.setCurrentPage(RESTORE_PAGE);
        try {
            List<Section> sectionList = sectionService.findAllRemovingSections();
            request.setAttribute(SECTION_LIST, sectionList);
            request.setAttribute(RESTORE_SECTION, true);
        } catch (ServiceException e) {
            throw new CommandException("Exception in a FindAllRemovingSectionsCommand class ", e);
        }
        return router;
    }
}
