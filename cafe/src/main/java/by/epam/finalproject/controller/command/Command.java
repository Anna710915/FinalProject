package by.epam.finalproject.controller.command;

import by.epam.finalproject.controller.Parameter;
import by.epam.finalproject.controller.Router;
import by.epam.finalproject.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * The interface Command.
 */
public interface Command {
    /**
     * The constant QUESTION_MARK.
     */
    String QUESTION_MARK = "?";
    /**
     * The constant EQUAL_SIGN.
     */
    String EQUAL_SIGN = "=";

    /**
     * Execute router.
     *
     * @param request the request
     * @return the router
     * @throws CommandException the command exception
     */
    Router execute(HttpServletRequest request) throws CommandException;

    /**
     * Create url string.
     *
     * @param request     the request
     * @param commandName the command name
     * @return the string
     */
    static String createURL(HttpServletRequest request, String commandName) {
        return request.getContextPath() + request.getServletPath()
                + QUESTION_MARK + Parameter.COMMAND + EQUAL_SIGN + commandName;
    }
}
