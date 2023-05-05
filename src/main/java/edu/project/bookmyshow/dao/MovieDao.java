package edu.project.bookmyshow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.project.bookmyshow.entity.Movie;
import edu.project.bookmyshow.repository.MovieRepo;

@Repository
public class MovieDao {
	
	@Autowired
	private MovieRepo movieRepo;
	
	public Movie addMovie(Movie movie) {
		return movieRepo.save(movie);
	}
	
	public Movie getMovie(long movieId) {
		Optional<Movie> optional = movieRepo.findById(movieId);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
	}
}
