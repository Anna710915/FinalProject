package by.epam.finalproject.controller;


import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.controller.factory.CommandType;
import by.epam.finalproject.exception.CommandException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static by.epam.finalproject.controller.PathPage.ERROR_500;
import static by.epam.finalproject.controller.Parameter.COMMAND;

/**
 * The type Controller class. Manage requests and forms responses for clients.
 * Override GET and POST methods.
 */
@WebServlet(urlPatterns = {"/controller"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 25)
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(COMMAND);
        logger.log(Level.DEBUG,"The command is " + commandName);
        Optional<Command> command = CommandType.provideCommand(commandName);
        try {
            Router router;
            if(command.isPresent()){
                router = command.get().execute(request);
                String page = router.getCurrentPage();
                if(router.getCurrentType() == Router.Type.FORWARD){
                    logger.log(Level.INFO,"Forward type.");
                    request.getRequestDispatcher(page).forward(request,response);
                }else{
                    logger.log(Level.INFO,"Redirect type.");
                    response.sendRedirect(page);
                }
            }else {
                response.sendRedirect(ERROR_500);
            }
        } catch (CommandException e) {
            logger.log(Level.ERROR, e);
            response.sendRedirect(ERROR_500);
        }
    }
}
