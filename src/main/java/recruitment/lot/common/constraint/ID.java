package recruitment.lot.common.constraint;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Min(1)
@NotNull
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@ReportAsSingleViolation
@Constraint(validatedBy = {})
public @interface ID {

    String message() default "ID must be greater than 0";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
