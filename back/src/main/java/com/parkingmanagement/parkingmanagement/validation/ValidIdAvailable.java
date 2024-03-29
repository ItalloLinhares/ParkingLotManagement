package com.parkingmanagement.parkingmanagement.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Constraint(validatedBy = { ValidIdAvailableValidator.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidIdAvailable {
        String message() default "Parking Space doesn't exist or is Unavailable";
        Class[] groups() default { };
        Class<? extends Payload>[] payload() default { };
}
