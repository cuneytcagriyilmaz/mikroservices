package com.cagri.kitaplik.library_service.dto;


public class AddBookRequest {

    private String id;
    private String isbn;

    // Default constructor
    public AddBookRequest() {
    }

    // Constructor with parameters
    public AddBookRequest(String id, String isbn) {
        this.id = id;
        this.isbn = isbn;
    }

    // Getter for id
    public String getId() {
        return id;
    }

    // Setter for id
    public void setId(String id) {
        this.id = id;
    }

    // Getter for isbn
    public String getIsbn() {
        return isbn;
    }

    // Setter for isbn
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
