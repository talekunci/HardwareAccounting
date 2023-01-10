package com.accounting.HardwareAccounting.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = UniqueValidation.class)
@Target({ElementType.TYPE})
@Retention(RUNTIME)
@Documented
public @interface IsUnique {

    String message() default "This object not unique.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String table() default "";

    String field() default "";

    Class<?> model();

}
