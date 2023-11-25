package by.bsuir.wtlab2.utils;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ClassScanner {
    private ClassScanner() {
    }

    public static List<Class<?>> scan(String packageName, Class<? extends Annotation> annotation) {
        List<Class<?>> classes = new ArrayList<>();
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(annotation);
        for (Class<?> c : annotated) {
            if (c.isInterface()) {
                continue;
            }
            classes.add(c);
        }
        return classes;
    }
}
