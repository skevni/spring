package ru.gb.sklyarov.shop.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> catchResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new ShopError(ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
