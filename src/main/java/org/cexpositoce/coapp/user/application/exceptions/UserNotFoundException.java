package org.cexpositoce.coapp.user.application.exceptions;

public class UserNotFoundException extends RuntimeException{
        public UserNotFoundException(String message) {
            super(message);
        }
}
