package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class BookJsonTests {

    @Autowired
    private JacksonTester<Book> bookJacksonTester;

    @Test
    void testSerialize() throws IOException {
        var book = Book.of("111", "java", "sai", 11.1);
        JsonContent<Book> jsonContent = bookJacksonTester.write(book);
        assertThat(jsonContent).extractingJsonPathStringValue("@.isbn").isEqualTo("111");
    }

    @Test
    void testDeserialize() throws IOException {
        var content = """
                    {
                        "isbn": "1111",
                        "title": "java",
                        "author":"sai",
                        "price":111.1
                    }
                """;
        assertThat(bookJacksonTester.parse(content)).usingRecursiveComparison().isEqualTo(Book.of("1111", "java", "sai", 111.1));
    }
}
