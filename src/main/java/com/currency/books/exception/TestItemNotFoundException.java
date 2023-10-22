package com.currency.books.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason ="Test item is not found")
public class TestItemNotFoundException extends RuntimeException {
    public TestItemNotFoundException(final String message) {
        super(message);
    }
}
