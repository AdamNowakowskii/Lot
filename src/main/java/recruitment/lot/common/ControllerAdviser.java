package recruitment.lot.common;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.ResponseEntity.status;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.query.sqm.PathElementException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import jakarta.validation.ConstraintViolationException;
import recruitment.lot.common.exception.ConflictException;
import recruitment.lot.common.exception.NotFoundException;
import recruitment.lot.common.exception.UnprocessableEntityException;

@RestControllerAdvice
public final class ControllerAdviser {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        return status(INTERNAL_SERVER_ERROR)
                .body(getGenericMessage("An unexpected exception has occurred"));
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<?> handleConflict(ConflictException e) {
        return status(CONFLICT).body(getGenericMessage(e));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFound(NotFoundException e) {
        return status(NOT_FOUND).body(getGenericMessage(e));
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<?> handleUnprocessableEntity(UnprocessableEntityException e) {
        return status(UNPROCESSABLE_ENTITY).body(getGenericMessage(e));
    }

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            HandlerMethodValidationException.class,
            ConstraintViolationException.class})
    public ResponseEntity<?> handleBadRequest(Exception e) {
        if (e instanceof MethodArgumentNotValidException v) {
            Map<String, String> errors = new HashMap<>();

            v.getBindingResult().getFieldErrors().forEach(error -> errors
                    .put(error.getField(), error.getDefaultMessage()));

            v.getBindingResult().getGlobalErrors().forEach(error -> errors
                    .put(error.getObjectName(), error.getDefaultMessage()));

            return status(BAD_REQUEST).body(getGenericMessage(errors));
        }

        if (e instanceof HandlerMethodValidationException v) {
            Map<String, String> errors = new HashMap<>();

            v.getAllValidationResults().forEach(error -> {
                String name = error.getMethodParameter().getParameterName();
                error.getResolvableErrors().forEach(m -> errors.put(name, m.getDefaultMessage()));
            });

            return status(BAD_REQUEST).body(getGenericMessage(errors));
        }

        if (e instanceof ConstraintViolationException v) {
            Map<String, String> errors = new HashMap<>();

            v.getConstraintViolations().forEach(error -> errors.put(
                    error.getPropertyPath().toString(), error.getMessage()));

            return status(BAD_REQUEST).body(getGenericMessage(errors));
        }

        if (e instanceof PathElementException) {
            return status(BAD_REQUEST).body((getGenericMessage("Invalid sorting parameter")));
        }

        return status(BAD_REQUEST).body(getGenericMessage(e));
    }

    private static Map<String, String> getGenericMessage(Exception e) {
        return Map.of("error", e.getMessage());
    }

    private static Map<String, Map<String, String>> getGenericMessage(Map<String, String> e) {
        return Map.of("error", e);
    }

    private static Map<String, String> getGenericMessage(String e) {
        return Map.of("error", e);
    }

}
