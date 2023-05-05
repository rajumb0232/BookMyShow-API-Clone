package edu.project.bookmyshow;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edu.project.bookmyshow.enums.Genre;

@SpringBootApplication
public class BookMyShowCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookMyShowCloneApplication.class, args);

	}

	@Bean
	public ModelMapper getMapper() {
		return new ModelMapper();
	}

}
