package by.epam.finalproject.controller.command.impl.admin;

import by.epam.finalproject.controller.Router;
import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.exception.CommandException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.service.impl.MenuServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static by.epam.finalproject.controller.Parameter.PRODUCT_NAME;
import static by.epam.finalproject.controller.Parameter.PRODUCT_COMPOSITION;
import static by.epam.finalproject.controller.Parameter.PRODUCT_WEIGHT;
import static by.epam.finalproject.controller.Parameter.PRODUCT_CALORIES;
import static by.epam.finalproject.controller.Parameter.PRODUCT_PRICE;
import static by.epam.finalproject.controller.Parameter.PRODUCT_TIME;
import static by.epam.finalproject.controller.Parameter.PRODUCT_DISCOUNT;
import static by.epam.finalproject.controller.Parameter.PRODUCT_SECTION;
import static by.epam.finalproject.controller.Parameter.INVALID_PRODUCT_SECTION;
import static by.epam.finalproject.controller.Parameter.INVALID_PRODUCT_WEIGHT;
import static by.epam.finalproject.controller.Parameter.INVALID_PRODUCT_CALORIES;
import static by.epam.finalproject.controller.Parameter.INVALID_PRODUCT_COMPOSITION;
import static by.epam.finalproject.controller.Parameter.INVALID_PRODUCT_DISCOUNT;
import static by.epam.finalproject.controller.Parameter.INVALID_PRODUCT_NAME;
import static by.epam.finalproject.controller.Parameter.INVALID_PRODUCT_PRICE;
import static by.epam.finalproject.controller.Parameter.NOT_UNIQ_PRODUCT_NAME;
import static by.epam.finalproject.controller.Parameter.INVALID_COOKING_TIME;
import static by.epam.finalproject.controller.Parameter.PRODUCT_ID;
import static by.epam.finalproject.controller.SessionAttribute.CURRENT_PAGE;

import static by.epam.finalproject.controller.PropertiesKey.INVALID_PRODUCT_COMPOSITION_MESSAGE;
import static by.epam.finalproject.controller.PropertiesKey.NOT_UNIQ_PRODUCT_NAME_MESSAGE;
import static by.epam.finalproject.controller.PropertiesKey.INVALID_PRODUCT_CALORIES_MESSAGE;
import static by.epam.finalproject.controller.PropertiesKey.INVALID_PRODUCT_DISCOUNT_MESSAGE;
import static by.epam.finalproject.controller.PropertiesKey.INVALID_PRODUCT_NAME_MESSAGE;
import static by.epam.finalproject.controller.PropertiesKey.INVALID_PRODUCT_PRICE_MESSAGE;
import static by.epam.finalproject.controller.PropertiesKey.INVALID_PRODUCT_WEIGHT_MESSAGE;
import static by.epam.finalproject.controller.PropertiesKey.INVALID_COOKING_TIME_MESSAGE;
import static by.epam.finalproject.controller.PropertiesKey.INVALID_PRODUCT_SECTION_MESSAGE;


/**
 * The type Update product command.
 */
public class UpdateProductCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final MenuServiceImpl service = MenuServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        Map<String,String> map = new HashMap<>();
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(CURRENT_PAGE);
        map.put(PRODUCT_NAME, request.getParameter(PRODUCT_NAME));
        logger.log(Level.INFO, request.getParameter(PRODUCT_NAME));
        map.put(PRODUCT_COMPOSITION, request.getParameter(PRODUCT_COMPOSITION));
        map.put(PRODUCT_WEIGHT, request.getParameter(PRODUCT_WEIGHT));
        map.put(PRODUCT_CALORIES, request.getParameter(PRODUCT_CALORIES));
        map.put(PRODUCT_TIME, request.getParameter(PRODUCT_TIME));
        map.put(PRODUCT_DISCOUNT, request.getParameter(PRODUCT_DISCOUNT));
        map.put(PRODUCT_PRICE, request. getParameter(PRODUCT_PRICE));
        map.put(PRODUCT_SECTION, request.getParameter(PRODUCT_SECTION));
        router.setCurrentPage(currentPage);
        try {
            long id = Long.parseLong(request.getParameter(PRODUCT_ID));
            if (service.updateProduct(id, map).isEmpty()) {
                for (String key : map.keySet()) {
                    String value = map.get(key);
                    logger.log(Level.INFO,"Invalid data: " + key);
                    switch (value) {
                        case INVALID_PRODUCT_COMPOSITION -> request.setAttribute(INVALID_PRODUCT_COMPOSITION, INVALID_PRODUCT_COMPOSITION_MESSAGE);
                        case NOT_UNIQ_PRODUCT_NAME -> request.setAttribute(NOT_UNIQ_PRODUCT_NAME, NOT_UNIQ_PRODUCT_NAME_MESSAGE);
                        case INVALID_PRODUCT_CALORIES -> request.setAttribute(INVALID_PRODUCT_CALORIES, INVALID_PRODUCT_CALORIES_MESSAGE);
                        case INVALID_PRODUCT_DISCOUNT -> request.setAttribute(INVALID_PRODUCT_DISCOUNT, INVALID_PRODUCT_DISCOUNT_MESSAGE);
                        case INVALID_PRODUCT_NAME -> request.setAttribute(INVALID_PRODUCT_NAME, INVALID_PRODUCT_NAME_MESSAGE);
                        case INVALID_PRODUCT_PRICE -> request.setAttribute(INVALID_PRODUCT_PRICE, INVALID_PRODUCT_PRICE_MESSAGE);
                        case INVALID_PRODUCT_WEIGHT -> request.setAttribute(INVALID_PRODUCT_WEIGHT, INVALID_PRODUCT_WEIGHT_MESSAGE);
                        case INVALID_COOKING_TIME -> request.setAttribute(INVALID_COOKING_TIME, INVALID_COOKING_TIME_MESSAGE);
                        case INVALID_PRODUCT_SECTION -> request.setAttribute(INVALID_PRODUCT_SECTION, INVALID_PRODUCT_SECTION_MESSAGE);
                    }
                }
                return router;
            }
            router.setRedirectType();
        } catch (ServiceException | NumberFormatException e) {
            throw new CommandException("Exception in a UpdateProductCommand class ", e);
        }
        return router;
    }
}
