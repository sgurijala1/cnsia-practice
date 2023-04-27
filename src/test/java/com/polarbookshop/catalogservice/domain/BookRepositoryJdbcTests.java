package com.polarbookshop.catalogservice.domain;

import com.polarbookshop.catalogservice.config.DataConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;


@DataJdbcTest
@Import(DataConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("integration")
public class BookRepositoryJdbcTests
{
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate;


    @Test
    void findBookByisbnWhenExisting(){
        var bookIsbn = "1234";
        var book = Book.of("1234","java","sai",1.1,"Polarsophia");
        jdbcAggregateTemplate.insert(book);
        Optional<Book> optionalBook = bookRepository.findByIsbn(bookIsbn);
        Assertions.assertThat(optionalBook).isPresent();
        Assertions.assertThat(book.isbn()).isEqualTo(optionalBook.get().isbn());
    }
}
