package com.testing.model.validation.constraint;

import com.testing.model.entity.type.Complexity;
import com.testing.model.validation.ComplexitySubsetValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ComplexitySubsetValidator.class)
public @interface ComplexitySubset {
    Complexity[] anyOf();
    String message() default "must be any of {anyOf}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
