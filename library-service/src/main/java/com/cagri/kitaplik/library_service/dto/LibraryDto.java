package com.cagri.kitaplik.library_service.dto;


import java.util.ArrayList;
import java.util.List;

public class LibraryDto {

    private String id;
    private List<BookDto> userBookList;

    public LibraryDto() {
        this.id = "";
        
        this.userBookList = new ArrayList<>();
    }

    public LibraryDto(String id, List<BookDto> userBookList) {
        this.id = id;
        this.userBookList = userBookList != null ? userBookList : new ArrayList<BookDto>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<BookDto> getUserBookList() {
        return userBookList;
    }

    public void setUserBookList(List<BookDto> userBookList) {
        this.userBookList = userBookList != null ? userBookList : new ArrayList<BookDto>();
    }
}