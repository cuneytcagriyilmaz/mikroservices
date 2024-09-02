package com.cagri.kitaplik.library_service.controller;

import com.cagri.kitaplik.library_service.dto.AddBookRequest;
import com.cagri.kitaplik.library_service.dto.LibraryDto;
import com.cagri.kitaplik.library_service.service.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/library")
public class LibraryController {
    //sunucuların nerede olduğunu hangi ip'ye gittigini görmek için ekleenne kodlar mevcut. Asagidaki command ise kodun normal hali
    Logger logger = LoggerFactory.getLogger(LibraryController.class);
    private final Environment environment;

    private final LibraryService libraryService;


    public LibraryController(Environment environment, LibraryService libraryService) {
        this.environment = environment;
        this.libraryService = libraryService;
    }

    @GetMapping("{id}")
    public ResponseEntity<LibraryDto> getLibraryById(@PathVariable String id) {
        return ResponseEntity.ok(libraryService.getAllBookInLibraryById(id));
    }

    @PostMapping
    public ResponseEntity<LibraryDto> createLibrary() {
        logger.info("Library created on port number " + environment.getProperty("local.server.port"));
        return ResponseEntity.ok(libraryService.createLibrary());
    }

    @PutMapping
    public ResponseEntity<Void> addBookToLibrary(@RequestBody AddBookRequest request) {
        libraryService.addBookToLibrary(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<String>> getAllLibrary() {
        return ResponseEntity.ok(libraryService.getAllLibraries());
    }
}


//package com.cagri.kitaplik.library_service.controller;
//
//import com.cagri.kitaplik.library_service.dto.AddBookRequest;
//import com.cagri.kitaplik.library_service.dto.LibraryDto;
//import com.cagri.kitaplik.library_service.service.LibraryService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/v1/library")
//public class LibraryController {
//
//    private final LibraryService libraryService;
//
//
//    public LibraryController(LibraryService libraryService) {
//        this.libraryService = libraryService;
//    }
//
//    @GetMapping("{id}")
//    public ResponseEntity<LibraryDto> getLibraryById(@PathVariable String id) {
//        return ResponseEntity.ok(libraryService.getAllBookInLibraryById(id));
//    }
//
//    @PostMapping
//    public ResponseEntity<LibraryDto> createLibrary() {
//        return ResponseEntity.ok(libraryService.createLibrary());
//    }
//
//    @PutMapping
//    public ResponseEntity<Void> addBookToLibrary(@RequestBody AddBookRequest request){
//        libraryService.addBookToLibrary(request);
//        return ResponseEntity.ok().build();
//    }
//}
