package com.cagri.kitaplik.book_service;

import com.cagri.kitaplik.book_service.model.Book;
import com.cagri.kitaplik.book_service.repository.BookRepository;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import net.devh.boot.grpc.server.serverfactory.GrpcServerConfigurer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableDiscoveryClient
public class BookServiceApplication implements CommandLineRunner {

    private final BookRepository bookRepository;

    public BookServiceApplication(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Book book1 = new Book("Dünyanın Gözü", 2000, "Robert Jordan", "İthaki Yayınevi", "123456");
        Book book2 = new Book("Yüzüklerin Efendisi", 1960, "J.R.R Tolkien", "Metis Yayıncılık", "456789");
        Book book3 = new Book("Harry Potter ve Felsefe Taşı", 1997, "J. K. Rowling", "YKB Yayınları", "987654");

        List<Book> bookList = bookRepository.saveAll(Arrays.asList(book1, book2, book3));

        System.out.println(bookList);
    }

    //gRPC protokolu icin. Bu Bean olmadan gRPC Server ayaga kalkmaz
    @Bean
    public GrpcServerConfigurer keepAliveServerConfigurer() {
        return serverBuilder -> {
            if (serverBuilder instanceof NettyServerBuilder) {
                ((NettyServerBuilder) serverBuilder).keepAliveTime(30, TimeUnit.SECONDS)
                        .keepAliveTimeout(5, TimeUnit.SECONDS).permitKeepAliveWithoutCalls(true);
            }
        };
    }

//    @Bean
//    public GrpcServerConfigurer keepAliveServerConfigurer() {
//        //Bu conf projeye gore farklilik gostermez. Copy Paste yapilabilir.
//        return serverBuilder -> {
//            if (serverBuilder instanceof NettyServerBuilder) {
//                ((NettyServerBuilder) serverBuilder).keepAliveTime(30, TimeUnit.SECONDS)
//                        .keepAliveTimeout(5, TimeUnit.SECONDS).permitKeepAliveWithoutCalls(true);
//            }
//        };
//    }

    public static void main(String[] args) {
        SpringApplication.run(BookServiceApplication.class, args);
    }

}
