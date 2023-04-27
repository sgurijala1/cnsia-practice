package com.polarbookshop.catalogservice.domain;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;


public class BookValidationTests {
    private static Validator validator;

    @BeforeAll
    static void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsArevalidNoError(){
        var book = Book.of("111","java", "Sai",111.1);
        Set<ConstraintViolation<Book>> validate = validator.validate(book);
        assertThat(validate).isEmpty();
    }

    @Test
    void whenISBNisNotValidThrowError(){
        var book = Book.of("","java", "Sai",111.1);
        Set<ConstraintViolation<Book>> validations = validator.validate(book);
        assertThat(validations).isNotEmpty();
        assertThat(validations).hasSize(1);
        assertThat(validations.iterator().next().getMessage()).isEqualTo("The book ISBN must be defined.");
    }
}
