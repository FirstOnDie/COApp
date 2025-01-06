package org.cexpositoce.coapp.login.application.exceptions.handlers;

import org.cexpositoce.coapp.common.exceptions.ErrorResponse;
import org.cexpositoce.coapp.login.application.exceptions.InvalidLoginException;
import org.cexpositoce.coapp.login.application.exceptions.InvalidRefreshTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InvalidRefreshTokenExceptionHandler {

    @ExceptionHandler(InvalidRefreshTokenException.class)
    public ResponseEntity<ErrorResponse> handleException(InvalidRefreshTokenException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.FORBIDDEN.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }
}