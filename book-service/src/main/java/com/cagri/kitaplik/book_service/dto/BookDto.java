package com.cagri.kitaplik.book_service.dto;


import com.cagri.kitaplik.book_service.model.Book;

public class BookDto {

    private BookIdDto id;
    private String title;
    private int bookYear;
    private String author;
    private String pressName;

    public BookDto() {
        // Varsayılan yapılandırıcı, id'yi null olarak ayarlar
        this(null, "", 0, "", "");
    }

    public BookDto(BookIdDto id, String title, int bookYear, String author, String pressName) {
        this.id = id;
        this.title = title;
        this.bookYear = bookYear;
        this.author = author;
        this.pressName = pressName;
    }

    public static BookDto convert(Book from) {
        return new BookDto(
                from.getId() != null ? BookIdDto.convert(from.getId(), from.getIsbn()) : null,
                from.getTitle(),
                from.getBookYear(),
                from.getAuthor(),
                from.getPressName()
        );
    }

    // Getter ve Setter metodları

    public BookIdDto getId() {
        return id;
    }

    public void setId(BookIdDto id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBookYear() {
        return bookYear;
    }

    public void setBookYear(int bookYear) {
        this.bookYear = bookYear;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPressName() {
        return pressName;
    }

    public void setPressName(String pressName) {
        this.pressName = pressName;
    }
}

