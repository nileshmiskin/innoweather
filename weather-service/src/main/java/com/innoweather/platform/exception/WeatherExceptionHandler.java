package com.innoweather.platform.exception;

import com.innoweather.platform.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class WeatherExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(WeatherExceptionHandler.class);

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleValidationException(ConstraintViolationException ex) {
        logger.debug("Validation Exception {}", new Object[]{ex.getStackTrace()});
        return new ResponseEntity<>(new ErrorResponse("VALIDATION_ERROR", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorResponse> handleHttpClientError(HttpClientErrorException ex) {
        logger.debug("Exception {}", ex.getStatusText());
        return new ResponseEntity<>(new ErrorResponse("APP_ERROR", ex.getMessage()), ex.getStatusCode());
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> handleApplicationError(Throwable ex) {
        logger.debug("Exception {}", new Object[]{ex.getStackTrace()});
        return new ResponseEntity<>(new ErrorResponse("SERVER_ERROR", ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
