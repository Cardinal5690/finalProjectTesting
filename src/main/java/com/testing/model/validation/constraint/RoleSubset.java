package com.testing.model.validation.constraint;

import com.testing.model.entity.type.Role;
import com.testing.model.validation.RoleSubsetValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RoleSubsetValidator.class)
public @interface RoleSubset {
    Role[] anyOf();
    String message() default "must be any of {anyOf}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
