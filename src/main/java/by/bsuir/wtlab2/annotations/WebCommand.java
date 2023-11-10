package by.bsuir.wtlab2.annotations;

import by.bsuir.wtlab2.constants.HttpMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface WebCommand {
    String mapping();

    HttpMethod method() default HttpMethod.GET;
}
