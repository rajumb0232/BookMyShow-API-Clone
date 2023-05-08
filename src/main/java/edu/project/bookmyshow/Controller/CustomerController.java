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

import edu.project.bookmyshow.dto.CustomerDto;
import edu.project.bookmyshow.entity.Customer;
import edu.project.bookmyshow.service.CustomerService;
import edu.project.bookmyshow.util.ResponseStructure;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@ApiOperation(value = "Save Customer", notes = " Api is used to save the Customer")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created"),
			@ApiResponse(code = 404, message = "Customer not found for the given id") })
	@PostMapping
	public ResponseEntity<ResponseStructure<CustomerDto>> saveCustomer(@Valid @RequestBody Customer customer) {
		return customerService.saveCustomer(customer);
	}

	@ApiOperation(value = "Delete Customer", notes = " Api is used to delete the Customer")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted"),
			@ApiResponse(code = 404, message = "Customer not found for the given  id") })
	@DeleteMapping
	public ResponseEntity<ResponseStructure<CustomerDto>> deleteCustomer(@RequestParam long customerId) {
		return customerService.deleteCustomer(customerId);
	}

	@ApiOperation(value = "Find Customer", notes = " Api is used to find the Customer")
	@ApiResponses(value = { @ApiResponse(code = 302, message = "Successfully fetched"),
			@ApiResponse(code = 404, message = "Customer not found for the given  id") })
	@GetMapping
	public ResponseEntity<ResponseStructure<CustomerDto>> getCustomer(@RequestParam long customerId) {
		return customerService.getCustomerById(customerId);
	}

	@ApiOperation(value = "Update Customer", notes = " Api is used to update the Customer")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully Updated"),
			@ApiResponse(code = 404, message = "Customer not found for the given  id") })
	@PutMapping
	public ResponseEntity<ResponseStructure<CustomerDto>> updateCustomer(@RequestParam long customerId,
			@Valid @RequestBody Customer customer) {
		return customerService.updateCustomer(customerId, customer);
	}

}
