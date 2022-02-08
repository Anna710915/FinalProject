package by.epam.finalproject.controller.filter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Xss protection filter protect an application from dangerous requests.
 */
public class XssProtectionFilter implements Filter{
    private static final Logger logger = LogManager.getLogger();
    private static final String CONTROLLER_PATTERN = "/controller?";
    private static final String REGEX_SCRIPT = "%3C|%3E|%27";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse =(HttpServletResponse) response;
        String requestURI = servletRequest.getRequestURI();
        logger.log(Level.INFO,"request URI: " + requestURI);
        String query = servletRequest.getQueryString();
        if(query != null){
            requestURI = CONTROLLER_PATTERN + query;
            logger.log(Level.INFO,"request URI: " + requestURI);
        }
        Pattern pattern = Pattern.compile(REGEX_SCRIPT);
        Matcher matcher = pattern.matcher(requestURI);
        if(matcher.find()){
            logger.log(Level.INFO,"find script: " + requestURI);
            servletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        chain.doFilter(request,response);
    }
}
