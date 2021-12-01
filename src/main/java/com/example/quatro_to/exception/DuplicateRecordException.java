package com.example.quatro_to.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateRecordException extends Exception{
    public DuplicateRecordException(String message) {
        super(message);
    }
}
