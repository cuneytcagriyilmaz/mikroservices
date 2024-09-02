package com.cagri.kitaplik.library_service.client;


import com.cagri.kitaplik.library_service.dto.BookDto;
import com.cagri.kitaplik.library_service.dto.BookIdDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//verilen application name sayesinde, feign client bu ip'yi sunucu adını ve hangi path'de çalışacağını Eureka'dan alıyor ve kullanıma açıyor.
@FeignClient(name = "book-service", path = "/v1/book")
public interface BookFeignClient {

//    @GetMapping("/isbn/{isbn}")
//    ResponseEntity<BookIdDto> getBookByIsbn(@PathVariable String isbn);
//
//    @GetMapping("/book/{id}")
//    ResponseEntity<BookDto> getBookById(@PathVariable String id);

    Logger logger = LoggerFactory.getLogger(BookFeignClient.class);

    @GetMapping("/isbn/{isbn}")
    @CircuitBreaker(name = "getBookByIsbnCircuitBreaker", fallbackMethod = "getBookFallBack")
        //falback metodu ismi ile yazılan metodun ismi aynı olmalı.
        //Amaç hata durumunda başka bir süreç yaparak handle etmek. Burada bir hata mesajı da dönülebilir.Anmcak bunun için mantıklı olan sol taraftaki gibi yapmak. Yani ilk baştaki gibi
    ResponseEntity<BookIdDto> getBookByIsbn(@PathVariable(value = "isbn") String isbn);

    // fallback metotları her zaman feign'te gönderilen parametre ve exception'u parametre alır
    default ResponseEntity<BookIdDto> getBookFallBack(String isbn, Exception exception) {
        logger.info("Book not found vy isbn: " + isbn + " , returning default BookDto object");
        return ResponseEntity.ok(new BookIdDto("default-book", "default-isbn"));
    }
    //Şuanda getBookByIsbn metodu çalışırken bir hata oluşursa aşağısındaki default getBookFallBack çalışacak.


    @GetMapping("/book/{id}")
    @CircuitBreaker(name = "getBookByIdCircuitBreaker", fallbackMethod = "getBookByIdFallBack")
    ResponseEntity<BookDto> getBookById(@PathVariable(value = "id") String bookId);

    default ResponseEntity<BookDto> getBookByIdFallBack(String bookId, Exception exception) {
        logger.info("Book not found by id: " + bookId + " , returning default BookDto object");
        return ResponseEntity.ok(new BookDto(new BookIdDto("default-book", "default-id")));
    }
}
