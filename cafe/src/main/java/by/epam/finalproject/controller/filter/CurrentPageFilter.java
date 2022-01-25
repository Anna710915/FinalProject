package by.epam.finalproject.controller.filter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.finalproject.controller.Parameter.COMMAND;
import static by.epam.finalproject.controller.SessionAttribute.CURRENT_PAGE;

/**
 * The type Current page filter.
 */
public class CurrentPageFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();
    private static final String CONTROLLER = "/controller?";
    private static final String QUESTION = "?";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpSession session = servletRequest.getSession();
        String requestURI = servletRequest.getRequestURI();
        logger.log(Level.INFO,"request URI: " + requestURI);
        String query = servletRequest.getQueryString();
        if(query != null){
            if(servletRequest.getParameter(COMMAND) != null) {
                requestURI = CONTROLLER + query;
            } else {
                requestURI = servletRequest.getContextPath() + servletRequest.getServletPath() + QUESTION + query;
            }
        }
        logger.log(Level.INFO, query);
        session.setAttribute(CURRENT_PAGE, requestURI);
        chain.doFilter(request, response);
    }
}
