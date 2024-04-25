package recruitment.lot.common.constraint;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.Pattern;

@Pattern(regexp = "^(\\+[0-9]{1,4}\\s[0-9]{1,12})$")
@Target(FIELD)
@Retention(RUNTIME)
@ReportAsSingleViolation
@Constraint(validatedBy = {})
public @interface PhoneNumber {

    String message() default "Phone number should start with a plus sign (+), " +
            "followed by 1 to 4 country code digits, " +
            "a space, and then up to 12 digits for the phone number.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
