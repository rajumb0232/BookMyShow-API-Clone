package edu.project.bookmyshow.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.project.bookmyshow.dto.MovieDto;
import edu.project.bookmyshow.entity.Movie;
import edu.project.bookmyshow.service.MovieService;
import edu.project.bookmyshow.util.ResponseStructure;

@RestController
@RequestMapping("/movie")
public class MovieController {
	@Autowired
	private MovieService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Movie>> saveMovie(@RequestParam long houseId,@RequestBody MovieDto movieDto){
		return service.saveMovie(houseId,movieDto);
	}
    @GetMapping
    public ResponseEntity<ResponseStructure<Movie>> getMovie(@RequestParam long movieId){
    	return service.getMovieById(movieId);
    }
    @DeleteMapping
    public ResponseEntity<ResponseStructure<Movie>> deleteMovie(@RequestParam long movieId){
    	return service.deleteMovieById(movieId);
    }
    @PutMapping
    public ResponseEntity<ResponseStructure<Movie>> updateMovie(@RequestParam long movieId,@RequestBody MovieDto movieDto){
    	return service.updateMovie(movieId, movieDto);
    }
}

