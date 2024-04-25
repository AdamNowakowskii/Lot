package recruitment.lot.common.constraint;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;

@Retention(RUNTIME)
@ReportAsSingleViolation
@Target({FIELD, PARAMETER})
@Constraint(validatedBy = IDsValidator.class)
public @interface IDs {

    String message() default "IDs must not be null and must be greater than 0";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
