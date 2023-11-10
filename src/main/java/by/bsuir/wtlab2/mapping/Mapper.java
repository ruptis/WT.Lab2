package by.bsuir.wtlab2.mapping;

import by.bsuir.wtlab2.annotations.Singleton;
import by.bsuir.wtlab2.controller.commands.Command;
import by.bsuir.wtlab2.controller.commands.CommandFactory;
import by.bsuir.wtlab2.exception.CommandException;
import by.bsuir.wtlab2.exception.MappingException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class Mapper {
    private final CommandFactory commandFactory;
    private final Map<CommandMapping, Class<? extends Command>> mappings = new HashMap<>();

    public Mapper(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    public void registerRoute(CommandMapping mapping, Class<? extends Command> commandType) {
        mappings.put(mapping, commandType);
    }

    public Command getCommand(HttpServletRequest request) throws MappingException, CommandException {
        var mapping = CommandMapping.of(request);
        var commandType = mappings.get(mapping);
        if (commandType == null) {
            throw new MappingException("No command for path " + mapping);
        }
        return commandFactory.createCommand(commandType);
    }
}
