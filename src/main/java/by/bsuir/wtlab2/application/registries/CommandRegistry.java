package by.bsuir.wtlab2.application.registries;


import by.bsuir.wtlab2.annotations.Singleton;
import by.bsuir.wtlab2.annotations.WebCommand;
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

    public CommandRegistry(DiContainer diContainer, Mapper mapper) {
        this.diContainer = diContainer;
        this.mapper = mapper;
    }

    public void register(String packageName) {
        List<Class<?>> classes = ClassScanner.scan(packageName, WebCommand.class);
        List<Class<? extends Command>> commandClasses = getCommandClasses(classes);
        commandClasses.forEach(diContainer::bind);
        commandClasses.forEach(c -> mapper.registerRoute(CommandMapping.of(c.getAnnotation(WebCommand.class)), c));
    }

    private List<Class<? extends Command>> getCommandClasses(List<Class<?>> classes) {
        List<Class<? extends Command>> list = new ArrayList<>();
        for (Class<?> c : classes)
            if (Command.class.isAssignableFrom(c))
                list.add(c.asSubclass(Command.class));
        return list;
    }
}
