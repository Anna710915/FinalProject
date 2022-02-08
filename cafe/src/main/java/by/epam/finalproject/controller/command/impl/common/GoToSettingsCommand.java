package by.epam.finalproject.controller.command.impl.common;

import by.epam.finalproject.controller.Router;
import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

import static by.epam.finalproject.controller.PathPage.SETTINGS_PAGE;

/**
 * The type Go to settings command.
 */
public class GoToSettingsCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        router.setCurrentPage(SETTINGS_PAGE);
        return router;
    }
}
