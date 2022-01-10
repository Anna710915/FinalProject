package by.epam.finalproject.controller.command;

import by.epam.finalproject.controller.Router;
import by.epam.finalproject.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    Router execute(HttpServletRequest request) throws CommandException;
}
