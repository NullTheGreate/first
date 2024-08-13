package com.first.first.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    public RestResponseEntityExceptionHandler() {
        super();
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleBadRequest(UserNotFoundException ex) {
        ErrorResponse errorResponse =
                new ErrorResponse(HttpStatus.NOT_FOUND.value(), "ex.getMessage()");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(UserCredentialException.class)
    public ResponseEntity<?> handleUserCredentialException(UserCredentialException ex) {
        ErrorResponse errorResponse =
                new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}
