package by.bsuir.wtlab2.application.registries;


import by.bsuir.wtlab2.annotations.CommandSecurity;
import by.bsuir.wtlab2.annotations.Singleton;
import by.bsuir.wtlab2.annotations.WebCommand;
import by.bsuir.wtlab2.application.SecurityConfig;
import by.bsuir.wtlab2.application.di.DiContainer;
import by.bsuir.wtlab2.controller.commands.Command;
import by.bsuir.wtlab2.mapping.CommandMapping;
import by.bsuir.wtlab2.mapping.Mapper;
import by.bsuir.wtlab2.utils.ClassScanner;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class CommandRegistry {
    private final DiContainer diContainer;
    private final Mapper mapper;
    private final SecurityConfig securityConfig;

    public CommandRegistry(DiContainer diContainer, Mapper mapper, SecurityConfig securityConfig) {
        this.diContainer = diContainer;
        this.mapper = mapper;
        this.securityConfig = securityConfig;
    }

    public void register(String packageName) {
        List<Class<?>> classes = ClassScanner.scan(packageName, WebCommand.class);
        List<Class<? extends Command>> commandClasses = getCommandClasses(classes);
        commandClasses.forEach(this::register);
    }

    private List<Class<? extends Command>> getCommandClasses(List<Class<?>> classes) {
        List<Class<? extends Command>> list = new ArrayList<>();
        for (Class<?> c : classes)
            if (Command.class.isAssignableFrom(c))
                list.add(c.asSubclass(Command.class));
        return list;
    }

    private void register(Class<? extends Command> commandClass) {
        CommandMapping mapping = CommandMapping.of(commandClass.getAnnotation(WebCommand.class));
        if (commandClass.isAnnotationPresent(CommandSecurity.class))
            registerSecurity(commandClass, mapping);
        mapper.registerRoute(mapping, commandClass);
        diContainer.bind(commandClass);
    }

    private void registerSecurity(Class<? extends Command> commandClass, CommandMapping mapping) {
        String[] roles = commandClass.getAnnotation(CommandSecurity.class).roles();
        securityConfig.registerCommandRoles(mapping, List.of(roles));
    }
}
