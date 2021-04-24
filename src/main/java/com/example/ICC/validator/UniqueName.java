package com.example.ICC.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = UniqueNameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueName {
    String message() default "This name is already in use, please choose another.";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
