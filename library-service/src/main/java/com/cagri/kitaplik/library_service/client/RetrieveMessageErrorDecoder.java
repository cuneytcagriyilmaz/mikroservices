package com.cagri.kitaplik.library_service.client;

import com.cagri.kitaplik.library_service.exception.BookNotFoundException;
import com.cagri.kitaplik.library_service.exception.ExceptionMessage;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class RetrieveMessageErrorDecoder implements ErrorDecoder {

//Feign'deki hatalar için kullanılır.
    // Önce feign'den gelen hata çözümlenir ve hata durumu belirlenmelidir.
    //Fign alınan cevabın hata olup olmadığını nasıl anlar ==> Alınan response 2200'den farklı ise, bu bir hatadır.
    //Burada Feig'den alınan hata kodunu decode etmek gerekiyor. Bu nedenle ErrorDecoder implement edilir.
    //alınan feign exceptiona default olarak çevirir. İstediğimiz gibi customize ederiz.


    //    private final ErrorDecoder errorDecoder = new ErrorDecoder.Default();
    // Buradaki Default her exception'da çalıştırılıyor. Biz bunu değiştirmek için override yapıyoruz. Artık default içinde bnim kodum çalışacak
    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        ExceptionMessage exceptionMessage = null;
        try (InputStream body = response.body().asInputStream()) {
            //input stream ile body'i okumaya başlıyoruz.
            // Aşağıdaki dependency ile karmaşık input stream'leri string e çevirmek yerine, stringbir metot ile kolayca çevirebiliriz
            /*
        <dependency>
			<groupId>commons-io</groupId>
			<artifactId>commons-io</artifactId>
			<version>2.16.1</version>
		</dependency>
             */
            //.headers aşağıda bir map. Bu map'te date key'ine karşı bir değer var oda time.

            exceptionMessage = new ExceptionMessage((String) response.headers().get("date").toArray()[0]
                    , response.status()
                    , HttpStatus.resolve(response.status()).getReasonPhrase(),
                    IOUtils.toString(body, StandardCharsets.UTF_8),
                    response.request().url());

        } catch (IOException e) {
            return new Exception(e.getMessage());
        }

        //Burada bir hata fırlatılmalı çünkü diğer general exception handler bunu akalamalı.
        switch (response.status()) {
            case 404:
                throw new BookNotFoundException(exceptionMessage);
            default:
                return errorDecoder.decode(methodKey, response);
        }
    }
}
