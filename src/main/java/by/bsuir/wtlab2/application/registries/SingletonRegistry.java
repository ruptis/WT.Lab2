package by.bsuir.wtlab2.application.registries;

import by.bsuir.wtlab2.annotations.Singleton;
import by.bsuir.wtlab2.application.di.DiContainer;
import by.bsuir.wtlab2.utils.ClassScanner;

import java.util.List;

public class SingletonRegistry {
    private final DiContainer diContainer;

    public SingletonRegistry(DiContainer diContainer) {
        this.diContainer = diContainer;
    }

    public void register(String packageName){
        List<Class<?>> classes = ClassScanner.scan(packageName, Singleton.class);
        classes.forEach(diContainer::bind);
    }
}
