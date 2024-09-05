package com.cagri.kitaplik.book_service.service;

import com.cagri.kitaplik.book_service.dto.BookIdDto;
import com.cagri.kitaplik.book_service.exception.BookNotFoundException;
import com.cagri.kitaplik.book_service.repository.BookRepository;
import com.kitaplik.bookservice.BookId;
import com.kitaplik.bookservice.BookServiceGrpc;
import com.kitaplik.bookservice.Isbn;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BookGrpcServiceImpl extends BookServiceGrpc.BookServiceImplBase {

    private static final Logger logger = LoggerFactory.getLogger(BookGrpcServiceImpl.class);
    private final BookRepository bookRepository;


    public BookGrpcServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //isbn ile bookId bilgisini repository uzerinden cekecegiz.
    @Override
    public void getBookIdByIsbn(Isbn request, StreamObserver<BookId> responseObserver) {
        logger.info("Grpc call received: " + request.getIsbn());
        BookIdDto bookIdDto = bookRepository.findBooksByIsbn(request.getIsbn())
                .map(book -> new BookIdDto(book.getId(), book.getIsbn()))
                .orElseThrow(() -> new BookNotFoundException("Book could not found by isbn: " + request.getIsbn()));
        //donus tipini responseObserver'a koymamiz gerek. BookId nesnesi de grpc'de yapili.
        //grpc de yapilmis class'a gidersek orada gorebiliriz neler almasi gerektigini. Biz bunun Builder'ini kullanacagiz
        responseObserver.onNext(
                BookId.newBuilder()
                        .setBookId(bookIdDto.getBookId())
                        .setIsbn(bookIdDto.getIsbn())
                        .build()
        );
        responseObserver.onCompleted(); //response degerini karsiya ilatmek icin kullanilir.
        super.getBookIdByIsbn(request, responseObserver);
    }
}
