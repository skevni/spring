package ru.gb.sklyarov.shop.common.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> catchResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new ShopError(HttpStatus.NOT_FOUND.value(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> catchDataValidationException(DataValidationException ex) {
        return new ResponseEntity<>(new ShopError(HttpStatus.BAD_REQUEST.value(), ex.getMessages()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> catchShopAuthException(ShopAuthException ex) {
        return new ResponseEntity<>(new ShopError(HttpStatus.UNAUTHORIZED.value(), ex.getMessages()), HttpStatus.UNAUTHORIZED);
    }
}
