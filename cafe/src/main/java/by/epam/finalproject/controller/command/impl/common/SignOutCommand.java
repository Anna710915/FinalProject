package by.epam.finalproject.controller.command.impl.common;

import by.epam.finalproject.controller.Router;
import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.epam.finalproject.controller.SessionAttribute.LANGUAGE;
import static by.epam.finalproject.controller.PathPage.HOME_PAGE;

/**
 * The type Sign out command.
 */
public class SignOutCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute(LANGUAGE);
        session.invalidate();
        session = request.getSession(true);
        session.setAttribute(LANGUAGE, language);
        router.setRedirectType();
        router.setCurrentPage(HOME_PAGE);
        return router;
    }
}

