package com.cagri.kitaplik.book_service.repository;

import com.cagri.kitaplik.book_service.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    Optional<Book> findBooksByIsbn(String isbn);
}
