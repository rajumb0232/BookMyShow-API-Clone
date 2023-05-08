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

import edu.project.bookmyshow.dto.ScreenDto;
import edu.project.bookmyshow.service.ScreenService;
import edu.project.bookmyshow.util.ResponseStructure;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/screen")
public class ScreenController {

	@Autowired
	private ScreenService screenService;

	@ApiOperation(value = "Save Screen", notes = " Api is used to save the Screen")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created"),
			@ApiResponse(code = 404, message = "Screen not found for the given  id") })
	@PostMapping
	public ResponseEntity<ResponseStructure<ScreenDto>> saveScreen(@RequestParam long theatreId,
			@Valid @RequestBody ScreenDto screenDto) {
		return screenService.saveScreen(theatreId, screenDto);
	}

	@ApiOperation(value = "Update Screen", notes = " Api is used to update the Screen")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully Updated"),
			@ApiResponse(code = 404, message = "Screen not found for the given  id") })
	@PutMapping
	public ResponseEntity<ResponseStructure<ScreenDto>> updateScreen(@RequestParam long screenId,
			@Valid @RequestBody ScreenDto screenDto) {
		return screenService.updateScreen(screenId, screenDto);
	}

	@ApiOperation(value = "Delete Screen", notes = " Api is used to delete the Screen")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted"),
			@ApiResponse(code = 404, message = "Screen not found for the given  id") })
	@DeleteMapping
	public ResponseEntity<ResponseStructure<ScreenDto>> deleteScreen(@RequestParam long screenId) {
		return screenService.deleteScreen(screenId);
	}

	@ApiOperation(value = "Find Screen", notes = " Api is used to find the Screen")
	@ApiResponses(value = { @ApiResponse(code = 302, message = "Successfully fetched"),
			@ApiResponse(code = 404, message = "Screen not found for the given  id") })
	@GetMapping
	public ResponseEntity<ResponseStructure<ScreenDto>> getScreen(@RequestParam long screenId) {
		return screenService.getScreenById(screenId);
	}
}
