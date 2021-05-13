package com.testing.model.validation;

import com.testing.model.entity.type.Status;
import com.testing.model.validation.constraint.StatusSubset;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class StatusSubsetValidator implements ConstraintValidator<StatusSubset, Status> {
    Status[] subset;

    @Override
    public void initialize(StatusSubset constraintAnnotation) {
        subset = constraintAnnotation.anyOf();
    }

    @Override
    public boolean isValid(Status status, ConstraintValidatorContext constraintValidatorContext) {
        return status == null || Arrays.asList(subset).contains(status);
    }
}
