package main.java.com.dmcs.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Object validator.
 *
 * Created by chrustu on 17.06.2015.
 */
@Aspect
@Component
public class ValidationAspect {

    @Autowired
    private Validator validator;

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    @Pointcut("execution(public * add*(..))")
    private void addingMovie() {}

    @Pointcut("within(pl.dmcs.service..*)")
    private void isService() {}

    @Pointcut("addingMovie() && isService()")
    private void addServiceOperation() {}

    @Around("pl.dmcs.apect.ValidationAspect.addServiceOperation()")
    private Object Validation(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        System.out.println("Validation");
        Object[] objects = proceedingJoinPoint.getArgs();
        Set<ConstraintViolation<Object>> validationResult = null;

        for (Object object : objects) {
            validationResult = validator.validate(object);

            if (validationResult.isEmpty()) {
                validationResult = null;
                proceedingJoinPoint.proceed();
            }
        }

        return validationResult;
    }
}
