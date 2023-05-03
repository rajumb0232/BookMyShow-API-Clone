package edu.project.bookmyshow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.project.bookmyshow.enums.Genre;

@SpringBootApplication
public class BookMyShowCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookMyShowCloneApplication.class, args);
		 Genre g = Genre.THRILLER;
		 System.out.println(g);
	}

}
