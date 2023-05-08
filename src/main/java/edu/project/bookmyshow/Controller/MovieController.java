package edu.project.bookmyshow.Controller;

import javax.validation.Valid;

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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/movie")
public class MovieController {
	@Autowired
	private MovieService service;

	@ApiOperation(value = "Save Movie", notes = " Api is used to save the Movie")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created"),
			@ApiResponse(code = 404, message = "Movie not found for the given  id") })
	@PostMapping
	public ResponseEntity<ResponseStructure<Movie>> saveMovie(@RequestParam long houseId,
			@Valid @RequestBody MovieDto movieDto) {
		return service.saveMovie(houseId, movieDto);
	}

	@ApiOperation(value = "Find Movie", notes = " Api is used to find the Movie")
	@ApiResponses(value = { @ApiResponse(code = 302, message = "Successfully fetched"),
			@ApiResponse(code = 404, message = "Movie not found for the given  id") })
	@GetMapping
	public ResponseEntity<ResponseStructure<Movie>> getMovie(@RequestParam long movieId) {
		return service.getMovieById(movieId);
	}

	@ApiOperation(value = "Delete Movie", notes = " Api is used to delete the Movie")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted"),
			@ApiResponse(code = 404, message = "Movie not found for the given  id") })
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Movie>> deleteMovie(@RequestParam long movieId) {
		return service.deleteMovieById(movieId);
	}

	@ApiOperation(value = "Update Movie", notes = " Api is used to update the Movie")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully Updated"),
			@ApiResponse(code = 404, message = "Movie not found for the given  id") })
	@PutMapping
	public ResponseEntity<ResponseStructure<Movie>> updateMovie(@RequestParam long movieId,
			@Valid @RequestBody MovieDto movieDto) {
		return service.updateMovie(movieId, movieDto);
	}
}
