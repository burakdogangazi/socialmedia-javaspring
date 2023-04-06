package com.rest.webservices.restfulwebservices.Exception;

import com.rest.webservices.restfulwebservices.user.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),"Total Errors:"+ ex.getErrorCount()+"First Error:"+ex.getFieldError().getDefaultMessage(), request.getDescription(false));


        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

}




/*
@ControllerAdvice açıklaması Spring MVC'de bir sınıfı genel bir istisna işleyici olarak tanımlamak için kullanılır. Spring MVC uygulamasındaki herhangi bir denetleyici yönteminde bir istisna fırlatıldığında, bu açıklama istisnayı yakalamaya ve merkezi bir şekilde ele almaya yardımcı olur.
@ControllerAdvice açıklaması, örneğin belirli bir HTTP durum kodu gönderme veya bir hata sayfası gösterme gibi istisna işleme davranışlarını özelleştirmenize olanak tanır. Bu açıklama aynı zamanda uygulamanın farklı bölgelerinde yaygın olarak kullanılan özel hata mesajlarını da yönetmenizi sağlar.
 */
