package recruitment.lot.common.exception;

import static java.lang.String.format;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public static NotFoundException forResourceNotFound(String resource, Long id) {
        return new NotFoundException(format("%s of ID %d doesn't exist", resource, id));
    }

}
