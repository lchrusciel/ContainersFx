package com.dmcs.constraint;

import com.dmcs.constraint.validator.YearConstraintValidator;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * Created by chrustu on 10.09.2015.
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = YearConstraintValidator.class)
public @interface YearConstraint {
    String message() default "Production year should be a year string!";
    Class[] groups() default {};
    Class[] payload() default {};
}
