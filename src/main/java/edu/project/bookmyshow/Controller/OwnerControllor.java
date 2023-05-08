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

import edu.project.bookmyshow.dto.OwnerDto;
import edu.project.bookmyshow.entity.Owner;
import edu.project.bookmyshow.service.OwnerService;
import edu.project.bookmyshow.util.ResponseStructure;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/owner")
public class OwnerControllor {

	@Autowired
	private OwnerService ownerService;

	@ApiOperation(value = "Save Owner", notes = " Api is used to save the Owner")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created"),
			@ApiResponse(code = 404, message = "Owner not found for the given  id") })
	@PostMapping
	public ResponseEntity<ResponseStructure<OwnerDto>> saveOwner(@Valid @RequestBody Owner owner) {
		return ownerService.saveOwner(owner);
	}

	@ApiOperation(value = "Find Owner", notes = " Api is used to find the Owner")
	@ApiResponses(value = { @ApiResponse(code = 302, message = "Successfully fetched"),
			@ApiResponse(code = 404, message = "Owner not found for the given  id") })
	@GetMapping
	public ResponseEntity<ResponseStructure<OwnerDto>> getOwnerById(@RequestParam long ownerId) {
		return ownerService.getOwnerById(ownerId);
	}

	@ApiOperation(value = "Delete Owner", notes = " Api is used to delete the Owner")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted"),
			@ApiResponse(code = 404, message = "Owner not found for the given  id") })
	@DeleteMapping
	public ResponseEntity<ResponseStructure<OwnerDto>> deleteOwnerById(@RequestParam long ownerId) {
		return ownerService.deleteOwnerById(ownerId);
	}

	@ApiOperation(value = "Update Owner", notes = " Api is used to update the Owner")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully Updated"),
			@ApiResponse(code = 404, message = "Owner not found for the given  id") })
	@PutMapping
	public ResponseEntity<ResponseStructure<OwnerDto>> updateOwner(@RequestParam long ownerId,
			@Valid @RequestBody Owner owner) {
		return ownerService.updateOwner(ownerId, owner);
	}

}
