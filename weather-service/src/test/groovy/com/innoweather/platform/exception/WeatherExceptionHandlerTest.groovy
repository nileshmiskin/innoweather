package com.innoweather.platform.exception

import com.innoweather.platform.model.ErrorResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.HttpClientErrorException
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

    def "It should convert any exception into an error response"() {
        given:
        Exception exception = new Exception("An exception")
        WeatherExceptionHandler handler = new WeatherExceptionHandler()
        ErrorResponse errorResponse = new ErrorResponse("SERVER_ERROR", "An exception")
        when:
        def responseEntity = handler.handleApplicationError(exception)
        then:
        responseEntity.statusCode == HttpStatus.INTERNAL_SERVER_ERROR
        responseEntity.body == errorResponse
    }

    def "It should convert HttpClientException to error response"() {
        given:
        WeatherExceptionHandler handler = new WeatherExceptionHandler()
        and:
        ErrorResponse errorResponse = new ErrorResponse("APP_ERROR", "404 statusText")
        HttpClientErrorException exception = new HttpClientErrorException.NotFound("statusText", new HttpHeaders(), null, null)
        when:
        def responseEntity = handler.handleHttpClientError(exception)
        then:
        responseEntity.statusCode == HttpStatus.NOT_FOUND
        responseEntity.body == errorResponse
    }
}
