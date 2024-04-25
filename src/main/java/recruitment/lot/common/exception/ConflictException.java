package recruitment.lot.common.exception;

import static java.lang.String.format;

public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message);
    }

    public static NotFoundException forExistingResource(String resource, String parameter) {
        return new NotFoundException(format("%s with parameter of '%s' already exists", resource, parameter));
    }

}
