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

import edu.project.bookmyshow.dto.TheatreDto;
import edu.project.bookmyshow.entity.Theatre;
import edu.project.bookmyshow.service.TheaterService;
import edu.project.bookmyshow.util.ResponseStructure;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/theater")
public class TheaterController {
	@Autowired
	private TheaterService service;

	@ApiOperation(value = "Save Theatre", notes = " Api is used to save the Theatre")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created"),
			@ApiResponse(code = 404, message = "Theatre not found for the given  id") })
	@PostMapping
	public ResponseEntity<ResponseStructure<Theatre>> addTheatre(@RequestParam long ownerId,
			@Valid @RequestBody TheatreDto dto) {
		return service.addTheatre(ownerId, dto);
	}

	@ApiOperation(value = "Update Theatre", notes = " Api is used to update the Theatre")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully Updated"),
			@ApiResponse(code = 404, message = "Theatre not found for the given  id") })
	@PutMapping
	public ResponseEntity<ResponseStructure<Theatre>> updateTheatre(@RequestParam long theaterId,
			@Valid @RequestBody TheatreDto dto) {
		return service.updateTheatre(theaterId, dto);
	}

	@ApiOperation(value = "Delete Theatre", notes = " Api is used to delete the Theatre")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted"),
			@ApiResponse(code = 404, message = "Theatre not found for the given  id") })
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Theatre>> deleteTheatre(@RequestParam long theaterId) {
		return service.deleteTheatre(theaterId);
	}

	@ApiOperation(value = "Find Theatre", notes = " Api is used to find the Theatre")
	@ApiResponses(value = { @ApiResponse(code = 302, message = "Successfully fetched"),
			@ApiResponse(code = 404, message = "Theatre not found for the given  id") })
	@GetMapping
	public ResponseEntity<ResponseStructure<Theatre>> getTheatre(@RequestParam long theaterId) {
		return service.findTheatre(theaterId);
	}

}
