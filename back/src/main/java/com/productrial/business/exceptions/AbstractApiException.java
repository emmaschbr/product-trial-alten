package com.productrial.business.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class AbstractApiException extends RuntimeException {

    @Getter
    private final HttpStatus errorStatus;

    AbstractApiException(HttpStatus errorStatus) {
        super();
        this.errorStatus = errorStatus;
    }

    AbstractApiException(String message, HttpStatus errorStatus) {
        super(message);
        this.errorStatus = errorStatus;
    }

    AbstractApiException(Throwable cause, HttpStatus errorStatus) {
        super(cause);
        this.errorStatus = errorStatus;
    }

    AbstractApiException(String message, Throwable cause, HttpStatus errorStatus) {
        super(message, cause);
        this.errorStatus = errorStatus;
    }

}
