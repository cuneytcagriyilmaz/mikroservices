package com.cagri.kitaplik.library_service.dto;


public class BookIdDto {

    private String id;
    private String isbn;

    // Default constructor
    public BookIdDto() {
    }

    // Constructor with parameters
    public BookIdDto(String id, String isbn) {
        this.id = id;
        this.isbn = isbn;
    }

    // Getter for bookId
    public String getBookId() {
        return id;
    }

    // Setter for bookId
    public void setBookId(String bookId) {
        this.id = bookId;
    }

    // Getter for isbn
    public String getIsbn() {
        return isbn;
    }

    // Setter for isbn
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // Static method to convert to BookIdDto
    public static BookIdDto convert(String id, String isbn) {
        return new BookIdDto(id, isbn);
    }
}