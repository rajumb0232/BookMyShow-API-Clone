package edu.project.bookmyshow.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.project.bookmyshow.dto.ShowDto;
import edu.project.bookmyshow.entity.Show;
import edu.project.bookmyshow.service.ShowService;
import edu.project.bookmyshow.util.ResponseStructure;

@RestController
@RequestMapping("/show")
public class ShowController {
	
	@Autowired
	private ShowService showService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Show>> addShow(@RequestBody ShowDto showDto, @RequestParam long movieId, @RequestParam long screenId){
		return showService.addShow(showDto, movieId, screenId);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Show>> getShow(@RequestParam long showId){
		return showService.getShow(showId);
	}
}
