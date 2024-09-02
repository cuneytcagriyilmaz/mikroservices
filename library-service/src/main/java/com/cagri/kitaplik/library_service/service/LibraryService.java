package com.cagri.kitaplik.library_service.service;


import com.cagri.kitaplik.library_service.client.BookFeignClient;
import com.cagri.kitaplik.library_service.dto.AddBookRequest;
import com.cagri.kitaplik.library_service.dto.LibraryDto;
import com.cagri.kitaplik.library_service.exception.LibraryNotFoundException;
import com.cagri.kitaplik.library_service.model.Library;
import com.cagri.kitaplik.library_service.repository.LibraryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryService {

    private final LibraryRepository libraryRepository;

    private final BookFeignClient bookFeignClient;

    public LibraryService(LibraryRepository libraryRepository, BookFeignClient bookFeignClient) {
        this.libraryRepository = libraryRepository;
        this.bookFeignClient = bookFeignClient;
    }


    public LibraryDto getAllBookInLibraryById(String id) {
        Library library = libraryRepository.findById(id)
                .orElseThrow(() -> new LibraryNotFoundException("Library could not found by id : " + id));
        LibraryDto libraryDto = new LibraryDto(library.getId(), library.getUserBook()
                .stream().map(bookFeignClient::getBookById)
                .map(ResponseEntity::getBody) // burada FeignClient çalışmaya başlar
                .collect(Collectors.toList()));
        return libraryDto;

    }

    public LibraryDto createLibrary() {
        Library newLibrary = libraryRepository.save(new Library());
        return new LibraryDto(newLibrary.getId(), null);
    }

    public void addBookToLibrary(AddBookRequest request) {
        String bookId = bookFeignClient.getBookByIsbn(request.getIsbn()).getBody().getBookId(); //kitabın id'sini bilmiyorum isbn nuamrasını biliyorum. Her kitabın vardır bu gerçek bilgi.
        Library library = libraryRepository.findById(request.getId())
                .orElseThrow(() -> new LibraryNotFoundException("Library colud not found by id: " + request.getId()));

        library.getUserBook()
                .add(bookId);
        libraryRepository.save(library);
    }

    //
    public List<String> getAllLibraries() {
        return libraryRepository.findAll().stream()
                .map(l -> l.getId()).collect(Collectors.toList());
    }
}
