package by.bsuir.wtlab2.service.implementations;

import by.bsuir.wtlab2.annotations.Singleton;
import by.bsuir.wtlab2.application.SecurityConfig;
import by.bsuir.wtlab2.constants.Role;
import by.bsuir.wtlab2.mapping.CommandMapping;
import by.bsuir.wtlab2.service.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
@RequiredArgsConstructor
public final class SecurityServiceImpl implements SecurityService {
    private final SecurityConfig securityConfig;

    @Override
    public boolean isAllowed(CommandMapping commandMapping, Role role) {
        log.debug("Checking if {} is allowed to access {}", role, commandMapping);
        if (role == null) return false;
        return securityConfig.getCommandRoles(commandMapping).contains(role);
    }

    @Override
    public boolean isSecured(CommandMapping commandMapping) {
        log.debug("Checking if {} is secured", commandMapping);
        return securityConfig.getCommandRoles(commandMapping) != null;
    }
}
