package com.cagri.kitaplik.library_service.dto;


public class BookDto {

    private BookIdDto id;
    private String title;
    private Integer year;
    private String author;
    private String pressName;

    // Default constructor
    public BookDto() {
    }

    // Constructor with parameters
    public BookDto(BookIdDto id, String title, Integer year, String author, String pressName) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.author = author;
        this.pressName = pressName;
    }

    public BookDto(BookIdDto bookIdDto) {
    }

    // Getter for id
    public BookIdDto getId() {
        return id;
    }

    // Setter for id
    public void setId(BookIdDto id) {
        this.id = id;
    }

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Setter for title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter for year
    public Integer getYear() {
        return year;
    }

    // Setter for year
    public void setYear(Integer year) {
        this.year = year;
    }

    // Getter for author
    public String getAuthor() {
        return author;
    }

    // Setter for author
    public void setAuthor(String author) {
        this.author = author;
    }

    // Getter for pressName
    public String getPressName() {
        return pressName;
    }

    // Setter for pressName
    public void setPressName(String pressName) {
        this.pressName = pressName;
    }
}

