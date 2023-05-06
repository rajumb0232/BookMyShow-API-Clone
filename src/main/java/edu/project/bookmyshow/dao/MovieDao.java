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
	public Movie updateMovie(long movieId,Movie movie) {
		Optional<Movie> optional = movieRepo.findById(movieId);
		if(optional.isPresent()) {
			movie.setMovieId(movieId);
			return movieRepo.save(movie);			
		}
		return null;
	}
	public Movie deleteMovie(long movieId) {
		Optional<Movie> optional = movieRepo.findById(movieId);
		if(optional.isPresent()) {
			movieRepo.delete(optional.get());
			return optional.get();
		}
	    return null;
	}
}
