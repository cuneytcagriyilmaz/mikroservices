package com.cagri.kitaplik.book_service.dto;


public class BookIdDto {

    private String id;
    private String isbn;

    public BookIdDto() {
        this("", "");
    }

    public BookIdDto(String id, String isbn) {
        this.id = id;
        this.isbn = isbn;
    }

    public static BookIdDto convert(String id, String isbn) {
        return new BookIdDto(id, isbn);
    }

    // Getter ve Setter metodları

    public String getBookId() {
        return id;
    }

    public void setBookId(String id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}

