package org.cexpositoce.coapp.login.application.exceptions;

public class InvalidRefreshTokenException extends RuntimeException{
        public InvalidRefreshTokenException(String message) {
            super(message);
        }
}
