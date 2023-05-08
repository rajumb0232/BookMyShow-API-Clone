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

import edu.project.bookmyshow.dto.AddressDto;
import edu.project.bookmyshow.service.AddressService;
import edu.project.bookmyshow.util.ResponseStructure;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@ApiOperation(value = "Save Address", notes = " Api is used to save the address")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created"),
			@ApiResponse(code = 404, message = "Address not found for the given  id") })
	@PostMapping
	public ResponseEntity<ResponseStructure<AddressDto>> saveAddress(@Valid @RequestBody AddressDto addressDto,
			@RequestParam long theatreId) {
		return addressService.saveAddress(addressDto, theatreId);
	}

	@ApiOperation(value = "Delete Address", notes = " Api is used to delete the address")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted"),
			@ApiResponse(code = 404, message = "Address not found for the given  id") })
	@DeleteMapping
	public ResponseEntity<ResponseStructure<AddressDto>> deleteAddress(@RequestParam long addressId) {
		return addressService.deleteAddress(addressId);
	}

	@ApiOperation(value = "Update Address", notes = " Api is used to update the address")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully Updated"),
			@ApiResponse(code = 404, message = "Address not found for the given  id") })
	@PutMapping
	public ResponseEntity<ResponseStructure<AddressDto>> updateAddress(@RequestParam long addressId,
			@Valid @RequestBody AddressDto addressDto) {
		return addressService.updateAddress(addressId, addressDto);
	}

	@ApiOperation(value = "Find Address", notes = " Api is used to find the address")
	@ApiResponses(value = { @ApiResponse(code = 302, message = "Successfully fetched"),
			@ApiResponse(code = 404, message = "Address not found for the given  id") })
	@GetMapping
	public ResponseEntity<ResponseStructure<AddressDto>> getAddressById(@RequestParam long addressId) {
		return addressService.getAddressById(addressId);
	}

}
