package by.bsuir.wtlab2.service;

import by.bsuir.wtlab2.mapping.CommandMapping;

public interface SecurityService {
    boolean isAllowed(CommandMapping commandMapping, String role);
    boolean isSecured(CommandMapping commandMapping);
}
