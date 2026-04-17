package com.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BookNotAvailableException extends RuntimeException {

    public BookNotAvailableException(Long bookId) {
        super("No available copies for book with id: " + bookId);
    }
}
