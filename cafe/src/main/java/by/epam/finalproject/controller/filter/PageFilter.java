package by.epam.finalproject.controller.filter;

import by.epam.finalproject.controller.filter.permission.PagePermission;
import by.epam.finalproject.model.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

import static by.epam.finalproject.controller.SessionAttribute.USER;
import static by.epam.finalproject.controller.PathPage.START_PAGE;

/**
 * The type Page filter.
 */
public class PageFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        String requestURI = httpRequest.getServletPath();
        logger.log(Level.INFO,"Page URI: " + requestURI);

        User.UserRole userRole = User.UserRole.GUEST;
        User user = (User) session.getAttribute(USER);
        if(user != null){
            userRole = user.getRole();
            logger.log(Level.INFO, userRole.toString());
        }
        logger.log(Level.INFO,userRole);
        boolean isCorrect;
        Set<String> pages;
        switch (userRole){
            case ADMIN -> {
                pages = PagePermission.ADMIN.getUserPages();
                logger.log(Level.INFO,pages);
                isCorrect = pages.stream().anyMatch(requestURI::contains);
            }
            case CLIENT -> {
                pages = PagePermission.CLIENT.getUserPages();
                logger.log(Level.INFO,pages);
                isCorrect = pages.stream().anyMatch(requestURI::contains);
            }
            default -> {
                pages = PagePermission.GUEST.getUserPages();
                isCorrect = pages.stream().anyMatch(requestURI::contains);
            }
        }
        if(!isCorrect && user == null){
            user = new User();
            user.setRole(User.UserRole.GUEST);
            session.setAttribute(USER,user);
            httpResponse.sendRedirect(START_PAGE);
            return;
        }else if(!isCorrect){
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        chain.doFilter(request,response);
    }
}
