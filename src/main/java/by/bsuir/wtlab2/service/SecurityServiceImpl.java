package by.bsuir.wtlab2.service;

import by.bsuir.wtlab2.annotations.Singleton;
import by.bsuir.wtlab2.application.SecurityConfig;
import by.bsuir.wtlab2.mapping.CommandMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class SecurityServiceImpl implements SecurityService {
    private final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);
    private final SecurityConfig securityConfig;

    public SecurityServiceImpl(SecurityConfig securityConfig) {
        this.securityConfig = securityConfig;
    }

    @Override
    public boolean isAllowed(CommandMapping commandMapping, String role) {
        logger.debug("Checking if {} is allowed to access {}", role, commandMapping);
        if (role == null) return false;
        return securityConfig.getCommandRoles(commandMapping).contains(role);
    }

    @Override
    public boolean isSecured(CommandMapping commandMapping) {
        logger.debug("Checking if {} is secured", commandMapping);
        return securityConfig.getCommandRoles(commandMapping) != null;
    }
}
