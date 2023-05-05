package edu.project.bookmyshow.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import edu.project.bookmyshow.dto.ScreenDto;
import edu.project.bookmyshow.service.ScreenService;
import edu.project.bookmyshow.util.ResponseStructure;

@RestController
public class ScreenController {

	@Autowired
	private ScreenService screenService;
	
	public ResponseEntity<ResponseStructure<ScreenDto>>saveScreen(long theatreId,ScreenDto screenDto){
		return screenService.saveScreen(theatreId,screenDto);
		
	}
	
}
