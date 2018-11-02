package com.innoweather.platform.exception

import com.innoweather.platform.model.ErrorResponse
import org.springframework.http.HttpStatus
import spock.lang.Specification

import javax.validation.ConstraintViolationException

class WeatherExceptionHandlerTest extends Specification {

    def "It should convert ConstraintViolationException into an error response"() {
        given:
        ConstraintViolationException ex = new ConstraintViolationException("An exception", null)
        WeatherExceptionHandler handler = new WeatherExceptionHandler()
        ErrorResponse errorResponse = new ErrorResponse("VALIDATION_ERROR", "An exception")

        when:
        def responseEntity = handler.handleValidationException(ex)

        then:
        responseEntity
        responseEntity.statusCode == HttpStatus.BAD_REQUEST
        responseEntity.body == errorResponse
    }

    def "It should convert any exception into an error response"(){
        given:
        Exception exception = new Exception("An exception")
        WeatherExceptionHandler handler = new WeatherExceptionHandler()
        ErrorResponse errorResponse = new ErrorResponse("APP_ERROR", "An exception")
        when:
        def responseEntity = handler.handleApplicationError(exception)
        then:
        responseEntity.statusCode ==  HttpStatus.INTERNAL_SERVER_ERROR
        responseEntity.body == errorResponse
    }
}
