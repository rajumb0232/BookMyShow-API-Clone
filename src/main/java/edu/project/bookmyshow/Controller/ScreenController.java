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

import edu.project.bookmyshow.dto.ScreenDto;
import edu.project.bookmyshow.service.ScreenService;
import edu.project.bookmyshow.util.ResponseStructure;

@RestController
@RequestMapping("/screen")
public class ScreenController {

	@Autowired
	private ScreenService screenService;

	@PostMapping
	public ResponseEntity<ResponseStructure<ScreenDto>> saveScreen(@RequestParam long theatreId,
			@RequestBody ScreenDto screenDto) {
		return screenService.saveScreen(theatreId, screenDto);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<ScreenDto>> updateScreen(@RequestParam long screenId,
			@RequestBody ScreenDto screenDto) {
		return screenService.updateScreen(screenId, screenDto);
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<ScreenDto>> deleteScreen(@RequestParam long screenId) {
		return screenService.deleteScreen(screenId);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<ScreenDto>>getScreen(@RequestParam long screenId){
		return screenService.getScreenById(screenId);
	}
}
