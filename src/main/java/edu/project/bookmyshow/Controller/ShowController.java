package edu.project.bookmyshow.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.project.bookmyshow.dto.ShowDto;
import edu.project.bookmyshow.entity.Show;
import edu.project.bookmyshow.service.ShowService;
import edu.project.bookmyshow.util.ResponseStructure;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/show")
public class ShowController {

	@Autowired
	private ShowService showService;

	@ApiOperation(value = "Save Show", notes = " Api is used to save the Show")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created"),
			@ApiResponse(code = 404, message = "Show not found for the given  id") })
	@PostMapping
	public ResponseEntity<ResponseStructure<Show>> addShow(@RequestBody ShowDto showDto, @RequestParam long movieId,
			@RequestParam long screenId) {
		return showService.addShow(showDto, movieId, screenId);
	}

	@ApiOperation(value = "Find Show", notes = " Api is used to find the Show")
	@ApiResponses(value = { @ApiResponse(code = 302, message = "Successfully fetched"),
			@ApiResponse(code = 404, message = "Show not found for the given  id") })
	@GetMapping
	public ResponseEntity<ResponseStructure<Show>> getShow(@RequestParam long showId) {
		return showService.getShow(showId);
	}
	
	@GetMapping("/city")
	public ResponseEntity<ResponseStructure<List<Show>>> getShowsByCity(@RequestParam String city){
		return showService.getShowsByCity(city);
	}
	
	@GetMapping("/movie")
	public ResponseEntity<ResponseStructure<List<Show>>> getShowsByMovieId(@RequestParam long movieId){
		return showService.getShowsByMovieId(movieId);
	}
	
	@PutMapping("/cancel")
	public ResponseEntity<ResponseStructure<Show>> cancelShow(@RequestParam long showId){
		return showService.cancelShow(showId);
	}
}
