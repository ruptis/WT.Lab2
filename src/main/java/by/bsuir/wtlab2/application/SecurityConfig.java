package by.bsuir.wtlab2.application;

import by.bsuir.wtlab2.annotations.Singleton;
import by.bsuir.wtlab2.mapping.CommandMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class SecurityConfig {
    private final Map<CommandMapping, List<String>> commandRoles = new HashMap<>();

    public void registerCommandRoles(CommandMapping mapping, List<String> roles) {
        commandRoles.put(mapping, roles);
    }

    public List<String> getCommandRoles(CommandMapping mapping) {
        return commandRoles.get(mapping);
    }
}
