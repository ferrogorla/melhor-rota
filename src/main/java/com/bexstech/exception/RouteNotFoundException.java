package com.bexstech.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RouteNotFoundException extends RuntimeException {

    public RouteNotFoundException(String message) {
        super( message );
    }

}
