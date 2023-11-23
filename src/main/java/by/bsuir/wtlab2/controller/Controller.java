package by.bsuir.wtlab2.controller;

import by.bsuir.wtlab2.application.di.DiContainer;
import by.bsuir.wtlab2.controller.commands.Command;
import by.bsuir.wtlab2.controller.commands.results.CommandResult;
import by.bsuir.wtlab2.controller.commands.results.NotFoundResult;
import by.bsuir.wtlab2.controller.commands.results.StatusCodeResult;
import by.bsuir.wtlab2.exception.CommandException;
import by.bsuir.wtlab2.exception.DiException;
import by.bsuir.wtlab2.exception.MappingException;
import by.bsuir.wtlab2.mapping.Mapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class Controller extends HttpServlet {
    private transient Mapper mapper;

    @Override
    public void init() throws ServletException {
        super.init();
        DiContainer resolver = (DiContainer) getServletContext().getAttribute("diContainer");
        try {
            mapper = resolver.resolve(Mapper.class);
        } catch (DiException e) {
            throw new ServletException("Failed to initialize controller.", e);
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        log.debug("Request: {} {}", req.getMethod(), req.getRequestURI());
        CommandResult result;
        try {
            Command command = mapper.getCommand(req);
            log.debug("Command: {}", command.getClass().getName());
            result = command.execute(req);
        } catch (MappingException e) {
            result = new NotFoundResult();
            log.trace("Failed to map request to command.", e);
        } catch (CommandException e) {
            result = new StatusCodeResult(500);
            log.trace("Failed to execute command.", e);
        }

        log.debug("Result: {}", result.getClass().getName());
        try {
            result.executeResult(req, resp);
        } catch (CommandException e) {
            log.error("Failed to execute command result.", e);
        }
    }
}
