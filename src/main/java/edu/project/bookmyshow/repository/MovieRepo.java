package edu.project.bookmyshow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.project.bookmyshow.entity.Movie;

public interface MovieRepo extends JpaRepository<Movie, Long>{

}
