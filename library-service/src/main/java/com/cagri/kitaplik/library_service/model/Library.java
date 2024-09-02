package com.cagri.kitaplik.library_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;


import java.util.ArrayList;
import java.util.List;

@Entity
public class Library {

    @Id
    @Column(name = "library_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @ElementCollection
    private List<String> userBook = new ArrayList<>();

    public Library() {
    }

    public Library(String id, List<String> userBook) {
        this.id = id;
        this.userBook = userBook;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getUserBook() {
        return userBook;
    }

    public void setUserBook(List<String> userBook) {
        this.userBook = userBook;
    }
}
