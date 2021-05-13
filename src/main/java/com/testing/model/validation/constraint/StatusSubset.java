package com.testing.model.validation.constraint;

import com.testing.model.entity.type.Status;
import com.testing.model.validation.StatusSubsetValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StatusSubsetValidator.class)
public @interface StatusSubset {
    Status[] anyOf();
    String message() default "must be any of {anyOf}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
