package com.am.training.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@SuppressWarnings("ALL")
@ControllerAdvice
class ApiExceptionHandler {


    @ExceptionHandler(value = {
            ApiRequestException.class,
            PersonNotFoundException.class,
            ColorNotFoundException.class,
            NoPersonsException.class,
            EmptyListException.class
    })
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
        // 1. Create a payload the api exception details
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException (
                e.getMessage (),
                badRequest,
                ZonedDateTime.now (ZoneId.of ("Z")));
        // 2. return response entity
        return new ResponseEntity<> (apiException, badRequest);
    }
}