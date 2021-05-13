package com.testing.model.validation;

import com.testing.model.entity.type.Role;
import com.testing.model.validation.constraint.RoleSubset;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class RoleSubsetValidator implements ConstraintValidator<RoleSubset, Role> {
    private Role[] subset;

    @Override
    public void initialize(RoleSubset constraintAnnotation) {
        subset = constraintAnnotation.anyOf();
    }

    @Override
    public boolean isValid(Role role, ConstraintValidatorContext constraintValidatorContext) {
        return role == null || Arrays.asList(subset).contains(role);
    }
}
