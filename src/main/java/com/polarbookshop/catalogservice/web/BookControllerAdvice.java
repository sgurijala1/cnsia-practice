package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.BookAlreadyExistsException;
import com.polarbookshop.catalogservice.domain.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class BookControllerAdvice {

    @ExceptionHandler(BookAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String bookAlreadyExists(BookAlreadyExistsException ex){
        return ex.getMessage();
    }


    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String bookNotFoundException(BookNotFoundException ex){
        return ex.getMessage();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> handleValidationErrors(MethodArgumentNotValidException ex){
        var errors = new HashMap<String,String>();
        ex.getBindingResult().getAllErrors().forEach( error -> {
            String fieldName = ((FieldError)error).getField();
            String errorMsg = error.getDefaultMessage();
            errors.put(fieldName,errorMsg);
        });
        return errors;
    }
}
