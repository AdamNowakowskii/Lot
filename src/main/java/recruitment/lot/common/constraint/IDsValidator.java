package recruitment.lot.common.constraint;

import java.util.Collection;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IDsValidator implements ConstraintValidator<IDs, Collection<Long>> {

    @Override
    public boolean isValid(Collection<Long> ids, ConstraintValidatorContext context) {
        return null == ids || ids.isEmpty() || ids.stream().noneMatch(id -> null == id || id < 1);
    }

}
