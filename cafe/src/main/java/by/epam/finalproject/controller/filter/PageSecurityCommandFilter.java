package by.epam.finalproject.controller.filter;

import by.epam.finalproject.controller.factory.CommandType;
import by.epam.finalproject.controller.filter.permission.UserPermission;
import by.epam.finalproject.model.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

import static by.epam.finalproject.controller.Parameter.COMMAND;
import static by.epam.finalproject.controller.Parameter.USER;

import static by.epam.finalproject.controller.PathPage.ERROR_404;

/**
 * The type Page security command filter.
 */
public class PageSecurityCommandFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        logger.log(Level.INFO,"PageSecurityFilter - doFilter");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        String command = httpServletRequest.getParameter(COMMAND);
        if (command == null){
            request.getRequestDispatcher(ERROR_404).forward(httpServletRequest,httpServletResponse);
            return;
        }
        User.UserRole role = User.UserRole.GUEST;
        Set<String> commands;

        User user = (User) session.getAttribute(USER);
        if(user != null){
            role = user.getRole();
        }

        switch (role){
            case ADMIN -> commands = UserPermission.ADMIN.getCommands();
            case CLIENT -> commands = UserPermission.CLIENT.getCommands();
            default -> commands = UserPermission.GUEST.getCommands();
        }

        boolean isCorrect = Arrays.stream(CommandType.values())
                        .anyMatch(commandType -> command.equalsIgnoreCase(commandType.toString()));

        if(isCorrect && !commands.contains(command.toUpperCase())){
            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        if (!commands.contains(command.toUpperCase())){
            logger.log(Level.INFO, "command = " + command);
            request.getRequestDispatcher(ERROR_404)
                    .forward(httpServletRequest,httpServletResponse);
            return;
        }
        logger.log(Level.INFO, "Chain continue");
        chain.doFilter(request, response);
    }

}
