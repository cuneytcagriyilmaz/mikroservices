package com.cagri.kitaplik.book_service.service;

import com.cagri.kitaplik.book_service.dto.BookDto;
import com.cagri.kitaplik.book_service.dto.BookIdDto;
import com.cagri.kitaplik.book_service.exception.BookNotFoundException;
import com.cagri.kitaplik.book_service.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream().map(BookDto::convert).collect(Collectors.toList());
    }

    public BookIdDto findByIsbn(String isbn) {
        return bookRepository.findBooksByIsbn(isbn).map(book -> new BookIdDto(book.getId(), book.getIsbn()))
                .orElseThrow(() -> new BookNotFoundException("Book could not found by isbn"));
    }

    public BookDto findBookDetailsById(String id) {
        return bookRepository.findById(id).map(BookDto::convert)
                .orElseThrow(() -> new BookNotFoundException("Book could not found by id: " + id));
    }
}
