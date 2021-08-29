package com.mirac.usermanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception to handle wrong password errors.
 *
 * @author miracy
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Password is wrong!")
public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException(String message){
        super(message);
    }
}
