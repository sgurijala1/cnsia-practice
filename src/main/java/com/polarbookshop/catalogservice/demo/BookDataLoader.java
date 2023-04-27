package com.polarbookshop.catalogservice.demo;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("testData")
public class BookDataLoader {

    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData(){
        bookRepository.deleteAll();
        var book1 =  Book.of("111", "Js", "Sai", 1.1,null);
        var book2 =  Book.of("112", "Js ed 2", "Sai", 2.1,null);
        bookRepository.saveAll(List.of(book1, book2));
    }

}
