package com.cagri.kitaplik.book_service.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String s) {
        super(s);
    }
}
