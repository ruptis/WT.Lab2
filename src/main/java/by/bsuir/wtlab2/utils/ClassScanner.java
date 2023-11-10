package by.bsuir.wtlab2.utils;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class ClassScanner {
    private ClassScanner() {
    }

    public static List<Class<?>> scan(String packageName, Class<? extends Annotation> annotation) {
        List<Class<?>> classes = new ArrayList<>();
        var reflections = new Reflections(packageName);
        var annotated = reflections.getTypesAnnotatedWith(annotation);
        for (var c : annotated) {
            if (c.isInterface()) {
                continue;
            }
            classes.add(c);
        }
        return classes;
    }
}
