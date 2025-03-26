package management.sedef.common.exception.handler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import management.sedef.common.exception.*;
import management.sedef.common.model.ErrorResponse;
import management.sedef.minio.exception.ApiError;
import management.sedef.minio.exception.FileResponseException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse handleJsonParseErrors(final HttpMessageNotReadableException exception) {
        log.error(exception.getMessage(), exception);

        if (exception.getCause() instanceof InvalidFormatException invalidFormatException) {
            return ErrorResponse.subErrors(invalidFormatException)
                    .header(ErrorResponse.Header.VALIDATION_ERROR.getName())
                    .build();
        }

        return ErrorResponse.builder()
                .header(ErrorResponse.Header.VALIDATION_ERROR.getName())
                .build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse handleValidationErrors(final MethodArgumentTypeMismatchException exception) {

        log.error(exception.getMessage(), exception);

        return ErrorResponse.subErrors(exception)
                .header(ErrorResponse.Header.VALIDATION_ERROR.getName())
                .build();
    }

    @ExceptionHandler(AbstractMaxLimitExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse handleMaxLimitExceededError(final AbstractMaxLimitExceededException exception) {
        log.error(exception.getMessage(), exception);

        return ErrorResponse.builder()
                .message(exception.getMessage())
                .header("MAX_LIMIT_EXCEEDED")
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse handleValidationErrors(final MethodArgumentNotValidException exception) {

        log.error(exception.getMessage(), exception);

        return ErrorResponse.subErrors(exception.getBindingResult().getFieldErrors())
                .header(ErrorResponse.Header.VALIDATION_ERROR.getName())
                .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse handlePathVariableErrors(final ConstraintViolationException exception) {
        log.error(exception.getMessage(), exception);

        return ErrorResponse.subErrors(exception.getConstraintViolations())
                .header(ErrorResponse.Header.VALIDATION_ERROR.getName())
                .build();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    ErrorResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        log.error(exception.getMessage(), exception);

        return ErrorResponse.builder()
                .header(ErrorResponse.Header.API_ERROR.getName())
                .build();
    }

    @ExceptionHandler(AbstractNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse handleNotExistError(final AbstractNotFoundException exception) {
        log.error(exception.getMessage(), exception);

        return ErrorResponse.builder()
                .header(ErrorResponse.Header.NOT_FOUND_ERROR.getName())
                .message(exception.getMessage())
                .build();
    }


    @ExceptionHandler(AbstractConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ErrorResponse handleAlreadyExistError(final AbstractConflictException exception) {
        log.error(exception.getMessage(), exception);

        return ErrorResponse.builder()
                .header(ErrorResponse.Header.CONFLICT_ERROR.getName())
                .message(exception.getMessage())
                .build();
    }


    @ExceptionHandler(AbstractAuthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ErrorResponse handleAuthError(final AbstractAuthException exception) {
        log.error(exception.getMessage(), exception);

        return ErrorResponse.builder()
                .header(ErrorResponse.Header.AUTH_ERROR.getName())
                .build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    ErrorResponse handleAccessDeniedError(final AccessDeniedException exception) {
        log.error(exception.getMessage(), exception);

        return ErrorResponse.builder()
                .header(ErrorResponse.Header.AUTH_ERROR.getName())
                .build();
    }


    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ErrorResponse handleSQLError(final SQLException exception) {
        log.error(exception.getMessage(), exception);

        return ErrorResponse.builder()
                .header(ErrorResponse.Header.DATABASE_ERROR.getName())
                .build();
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ErrorResponse handleDataAccessException(DataAccessException exception) {

        log.error(exception.getMessage(), exception);

        return ErrorResponse.builder()
                .header(ErrorResponse.Header.DATABASE_ERROR.getName())
                .build();
    }


    @ExceptionHandler(AbstractServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ErrorResponse handleProcessError(final AbstractServerException exception) {
        log.error(exception.getMessage(), exception);

        return ErrorResponse.builder()
                .header(ErrorResponse.Header.PROCESS_ERROR.getName())
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ErrorResponse handleProcessError(final Exception exception) {
        log.error(exception.getMessage(), exception);

        return ErrorResponse.builder()
                .header(ErrorResponse.Header.PROCESS_ERROR.getName())
                .build();
    }

    @ExceptionHandler(AbstractValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(final AbstractValidationException exception) {
        log.error(exception.getMessage(), exception);

        return ErrorResponse.builder()
                .header(ErrorResponse.Header.VALIDATION_ERROR.getName())
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(FileResponseException.class)
    public ResponseEntity<Object> handleFileResponseNotFoundException(FileResponseException ex) {

        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

        ApiError err = new ApiError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, LocalDateTime.now() ,
                "FileResponseException", details);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
}
