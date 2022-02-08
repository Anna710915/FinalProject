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
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

import static by.epam.finalproject.controller.SessionAttribute.CURRENT_PAGE;
import static by.epam.finalproject.controller.Parameter.NEW_SECTION_NAME;
import static by.epam.finalproject.controller.Parameter.PRODUCT_SECTION;
import static by.epam.finalproject.controller.Parameter.INVALID_PRODUCT_SECTION;
import static by.epam.finalproject.controller.Parameter.INVALID_NEW_SECTION_NAME;
import static by.epam.finalproject.controller.Parameter.NOT_UNIQ_NEW_SECTION_NAME;
import static by.epam.finalproject.controller.SessionAttribute.SECTION_LIST;

import static by.epam.finalproject.controller.PathPage.ERROR_500;

import static by.epam.finalproject.controller.PropertiesKey.INVALID_PRODUCT_SECTION_MESSAGE;
import static by.epam.finalproject.controller.PropertiesKey.INVALID_SECTION_NAME_MESSAGE;
import static by.epam.finalproject.controller.PropertiesKey.NOT_UNIQ_SECTION_NAME_MESSAGE;

/**
 * The type Update section name command.
 */
public class UpdateSectionNameCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final SectionService service = SectionServiceImpl.getInstance();
    private final Validator validator = ValidatorImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        String currentPage = (String) session.getAttribute(CURRENT_PAGE);
        String sectionName = request.getParameter(NEW_SECTION_NAME);
        router.setCurrentPage(currentPage);
        try {
            boolean result = true;
            if(request.getParameter(PRODUCT_SECTION) == null){
                request.setAttribute(INVALID_PRODUCT_SECTION, INVALID_PRODUCT_SECTION_MESSAGE);
                result = false;
            }

            if (!validator.isCorrectSectionName(sectionName)) {
                request.setAttribute(INVALID_NEW_SECTION_NAME, INVALID_SECTION_NAME_MESSAGE);
                result = false;
            }

            if (service.findSectionByName(sectionName).isPresent()) {
                Section findSection = service.findSectionByName(sectionName).get();
                if(!findSection.getSectionName().equals(sectionName)) {
                    request.setAttribute(NOT_UNIQ_NEW_SECTION_NAME, NOT_UNIQ_SECTION_NAME_MESSAGE);
                    result = false;
                }
            }

            if(!result){
                return router;
            }
            long sectionId = Long.parseLong(request.getParameter(PRODUCT_SECTION));
            Optional<Section> oldSection = service.updateSectionName(new Section(sectionId, sectionName, true));
            router.setRedirectType();
            if(oldSection.isPresent()) {
                List<Section> sectionList = service.findAllSections();
                context.setAttribute(SECTION_LIST, sectionList);
            }else {
                logger.log(Level.WARN, "Incorrect update section name. Section id = " + sectionId);
                router.setCurrentPage(ERROR_500);
                return router;
            }
        } catch (ServiceException | NumberFormatException e) {
            throw new CommandException("Exception in a UpdateSectionNameCommand class. ", e);
        }
        return router;
    }
}
