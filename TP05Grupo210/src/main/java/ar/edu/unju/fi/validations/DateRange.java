package ar.edu.unju.fi.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = DateRangeValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateRange {
	String message() default "La fecha debe estar entre el 1 de enero de 1950 y el 31 de diciembre de 2007";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}