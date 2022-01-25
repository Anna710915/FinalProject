package by.epam.finalproject.controller.command.impl.admin;

import by.epam.finalproject.controller.Router;
import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.exception.CommandException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.entity.Section;
import by.epam.finalproject.model.service.SectionService;
import by.epam.finalproject.model.service.impl.SectionServiceImpl;
import by.epam.finalproject.validator.Validator;
import by.epam.finalproject.validator.impl.ValidatorImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import static by.epam.finalproject.controller.Parameter.SECTION_NAME;
import static by.epam.finalproject.controller.SessionAttribute.CURRENT_PAGE;
import static by.epam.finalproject.controller.Parameter.INVALID_SECTION_NAME;
import static by.epam.finalproject.controller.SessionAttribute.SECTION_LIST;
import static by.epam.finalproject.controller.Parameter.NOT_UNIQ_SECTION_NAME;
import static by.epam.finalproject.controller.PropertiesKey.INVALID_SECTION_NAME_MESSAGE;
import static by.epam.finalproject.controller.PropertiesKey.NOT_UNIQ_SECTION_NAME_MESSAGE;


/**
 * The type Insert new section command.
 */
public class InsertNewSectionCommand implements Command {
    private final SectionService service = SectionServiceImpl.getInstance();
    private final Validator validator = ValidatorImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        String sectionName = request.getParameter(SECTION_NAME);
        String currentPage = (String) session.getAttribute(CURRENT_PAGE);
        router.setCurrentPage(currentPage);
        try {
            if(!validator.isCorrectSectionName(sectionName)){
                request.setAttribute(INVALID_SECTION_NAME, INVALID_SECTION_NAME_MESSAGE);
                return router;
            }

            if(service.findSectionByName(sectionName).isPresent()){
                request.setAttribute(NOT_UNIQ_SECTION_NAME, NOT_UNIQ_SECTION_NAME_MESSAGE);
                return router;
            }

            service.addNewSection(sectionName);
            List<Section> sectionList = service.findAllSections();
            if(!sectionList.isEmpty()){
                context.setAttribute(SECTION_LIST, sectionList);
            }

            router.setRedirectType();
        } catch (ServiceException e) {
            throw new CommandException("Exception in a InsertNewSectionCommand class. ", e);
        }
        return router;
    }
}
