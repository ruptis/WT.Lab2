package by.bsuir.wtlab2.controller.commands;

import by.bsuir.wtlab2.annotations.Singleton;
import by.bsuir.wtlab2.application.di.Resolver;
import by.bsuir.wtlab2.exception.CommandException;

@Singleton
public final class CommandFactory {
    private final Resolver resolver;

    public CommandFactory(Resolver resolver) {
        this.resolver = resolver;
    }

    public Command createCommand(Class<? extends Command> commandType) throws CommandException {
        try {
            return resolver.resolve(commandType);
        } catch (Exception e) {
            throw new CommandException("Failed to create command", e);
        }
    }
}
