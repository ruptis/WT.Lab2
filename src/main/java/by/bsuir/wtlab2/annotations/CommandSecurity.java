package by.bsuir.wtlab2.annotations;

import by.bsuir.wtlab2.constants.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandSecurity {
    Role[] roles() default {};
    Role[] except() default {};
}
