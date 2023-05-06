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

import edu.project.bookmyshow.dto.AddressDto;
import edu.project.bookmyshow.service.AddressService;
import edu.project.bookmyshow.util.ResponseStructure;

@RestController
@RequestMapping("/address")
public class AddressController {


	@Autowired
	private AddressService addressService;

	@PostMapping
	public ResponseEntity<ResponseStructure<AddressDto>> saveAddress(@RequestBody AddressDto addressDto,@RequestParam long theatreId) {
		return addressService.saveAddress(addressDto,theatreId);
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<AddressDto>> deleteAddress(@RequestParam long addressId) {
		return addressService.deleteAddress(addressId);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<AddressDto>> updateAddress(@RequestParam long addressId,
			@RequestBody AddressDto addressDto) {
		return addressService.updateAddress(addressId, addressDto);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<AddressDto>> getAddressById(@RequestParam long addressId) {
		return addressService.getAddressById(addressId);
	}

}
