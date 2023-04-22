package com.polarbookshop.catalogservice;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class CatalogServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(CatalogServiceApplication.class, args);
	}

}
