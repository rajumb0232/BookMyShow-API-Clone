package edu.project.bookmyshow.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

@RestController
@RequestMapping("/theater")
public class TheaterController {
	@Autowired
	private TheaterService service;

	@PostMapping
	public ResponseEntity<ResponseStructure<Theatre>> addTheatre(@RequestParam long ownerId,
			@RequestBody TheatreDto dto) {
		return service.addTheatre(ownerId, dto);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Theatre>> updateTheatre(@RequestParam long theaterId,
			@RequestBody TheatreDto dto) {
		return service.updateTheatre(theaterId, dto);
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<Theatre>> deleteTheatre(@RequestParam long theaterId) {
		return service.deleteTheatre(theaterId);
	}

}
