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

import edu.project.bookmyshow.dto.CustomerDto;
import edu.project.bookmyshow.entity.Customer;
import edu.project.bookmyshow.service.CustomerService;
import edu.project.bookmyshow.util.ResponseStructure;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping
	public ResponseEntity<ResponseStructure<CustomerDto>> saveCustomer(@RequestBody Customer customer) {
		return customerService.saveCustomer(customer);
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<CustomerDto>> deleteCustomer(@RequestParam long customerId) {
		return customerService.deleteCustomer(customerId);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<CustomerDto>> getCustomer(@RequestParam long customerId) {
		return customerService.getCustomerById(customerId);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<CustomerDto>> updateCustomer(@RequestParam long customerId,
			@RequestBody Customer customer) {
		return customerService.updateCustomer(customerId, customer);
	}

}
