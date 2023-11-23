package by.bsuir.wtlab2.service;

import by.bsuir.wtlab2.constants.Role;
import by.bsuir.wtlab2.mapping.CommandMapping;

public interface SecurityService {
    boolean isAllowed(CommandMapping commandMapping, Role role);
    boolean isSecured(CommandMapping commandMapping);
}
