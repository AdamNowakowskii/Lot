package recruitment.lot.common.exception;

import static java.lang.String.format;

import java.util.Collection;

public class UnprocessableEntityException extends RuntimeException {

    public UnprocessableEntityException(String message) {
        super(message);
    }

    public static UnprocessableEntityException forInvalidSubset(String subject, Collection<Long> ids) {
        return new UnprocessableEntityException(format("%s with the following IDs don't exist: %s", subject, ids));
    }

}
