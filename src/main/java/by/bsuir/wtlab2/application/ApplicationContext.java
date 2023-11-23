package by.bsuir.wtlab2.application;

import by.bsuir.wtlab2.application.di.DiContainer;
import by.bsuir.wtlab2.application.registries.CommandRegistry;
import by.bsuir.wtlab2.application.registries.SingletonRegistry;
import by.bsuir.wtlab2.exception.DiException;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApplicationContext implements ServletContextListener {
    private static final String PACKAGE_NAME = "by.bsuir.wtlab2";
    public DiContainer diContainer;

    public ApplicationContext() {
        diContainer = new DiContainer();
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextListener.super.contextInitialized(sce);
        diContainer.bind(SingletonRegistry.class);
        try {
            SingletonRegistry singletonRegistry = diContainer.resolve(SingletonRegistry.class);
            singletonRegistry.register(PACKAGE_NAME);
            CommandRegistry commandRegistry = diContainer.resolve(CommandRegistry.class);
            commandRegistry.register(PACKAGE_NAME);
        } catch (DiException e) {
            log.error("Failed to initialize application context", e);
        }
        sce.getServletContext().setAttribute("diContainer", diContainer);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
        diContainer.destroy();
    }
}
