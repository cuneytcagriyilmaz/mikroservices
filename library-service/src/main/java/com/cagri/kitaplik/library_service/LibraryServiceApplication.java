package com.cagri.kitaplik.library_service;

import com.cagri.kitaplik.library_service.client.RetrieveMessageErrorDecoder;
import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class LibraryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryServiceApplication.class, args);
    }

    //Resilience kullanacağımız içinm aşağıdakileri kaldırdık.
//    //Feign hataları için yazdığımız custom exception'u bean olarka tanımlamak gerekir. Feign client error handling
//    @Bean
//    public ErrorDecoder errorDecoder() {
//        return new RetrieveMessageErrorDecoder();
//    }
//
//    //Feihn log seviyesi de ayarlanabilir bean olarak
//    @Bean
//    Logger.Level feignErrorLevel() {
//        return Logger.Level.FULL;
//    }


}
