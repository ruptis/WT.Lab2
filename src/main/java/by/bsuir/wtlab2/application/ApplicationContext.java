package by.bsuir.wtlab2.application;

import by.bsuir.wtlab2.application.di.DiContainer;
import by.bsuir.wtlab2.application.registries.CommandRegistry;
import by.bsuir.wtlab2.application.registries.SingletonRegistry;
import by.bsuir.wtlab2.exception.DiException;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationContext implements ServletContextListener {
    private final Logger logger = LoggerFactory.getLogger(ApplicationContext.class);

    public static final String PACKAGE_NAME = "by.bsuir.wtlab2";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextListener.super.contextInitialized(sce);
        var diContainer = DiContainer.initialize();
        diContainer.bind(SingletonRegistry.class);
        try {
            var singletonRegistry = diContainer.resolve(SingletonRegistry.class);
            singletonRegistry.register(PACKAGE_NAME);
            var commandRegistry = diContainer.resolve(CommandRegistry.class);
            commandRegistry.register(PACKAGE_NAME);
        } catch (DiException e) {
            logger.error("Failed to initialize application context", e);
        }
    }
}
