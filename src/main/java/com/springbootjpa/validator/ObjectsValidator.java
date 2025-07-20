package com.springbootjpa.validator;

import com.springbootjpa.exception.ObjectValidationException;
import org.springframework.stereotype.Component;


import javax.validation.*;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ObjectsValidator<T> {

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    public Set<String> validate(T objectToValidate){
        Set<ConstraintViolation<T>> violations = validator.validate(objectToValidate);
        if(!violations.isEmpty()){
            Set<String> errorMsg = violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());
            throw new ObjectValidationException(errorMsg, objectToValidate.getClass().getSimpleName());
        }
        return Collections.emptySet();
    }
}