package com.dmcs.constraint.validator;

import com.dmcs.constraint.YearConstraint;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Year;
import java.time.format.DateTimeParseException;

/**
 * Created by chrustu on 10.09.2015.
 */
public class YearConstraintValidator implements ConstraintValidator<YearConstraint, String> {

    private Log log = LogFactory.getLog(YearConstraintValidator.class);

    @Override
    public void initialize(YearConstraint constraint) {

    }

    @Override
    public boolean isValid(String yersString, ConstraintValidatorContext constraintValidatorContext) {
        try {
            Year.parse(yersString);
            return true;
        } catch (DateTimeParseException exception) {
            return false;
        }
    }
}
