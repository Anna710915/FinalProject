package by.epam.finalproject.controller.command.impl.admin;

import by.epam.finalproject.controller.Router;
import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.exception.CommandException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.service.MenuService;
import by.epam.finalproject.model.service.impl.MenuServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import static by.epam.finalproject.controller.Parameter.PICTURE_PATH;
import static by.epam.finalproject.controller.Parameter.PRODUCT_NAME;
import static by.epam.finalproject.controller.SessionAttribute.CURRENT_PAGE;

import static by.epam.finalproject.controller.PathPage.ERROR_500;


/**
 * The type Upload product photo command.
 */
public class UploadProductPhotoCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String ABSOLUTE_PATH = "C:/Users/admin/source/picture/";
    private final MenuService service = MenuServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        try (InputStream inputStream = request.getPart(PICTURE_PATH).getInputStream()){
            String submittedFileName = request.getPart(PICTURE_PATH).getSubmittedFileName();
            String path = ABSOLUTE_PATH + submittedFileName;
            Path imagePath = new File(path).toPath();
            long bytes = Files.copy(
                    inputStream,
                    imagePath,
                    StandardCopyOption.REPLACE_EXISTING);
            logger.log(Level.INFO,"Upload result is successfully " + bytes + " " + path);
            String name = request.getParameter(PRODUCT_NAME);

            if(!service.updateProductPhoto(path, name)){
                logger.log(Level.INFO, "Update product photo is failed");
                router.setCurrentPage(ERROR_500);
                return router;
            }
        } catch (IOException | ServletException | ServiceException e) {
            throw new CommandException("Upload photo failed ", e);
        }
        HttpSession session = request.getSession();
        String current_page = (String) session.getAttribute(CURRENT_PAGE);
        logger.log(Level.INFO,"Upload photo current page is " + current_page);
        router.setCurrentPage(current_page);
        return router;
    }
}
