package edu.project.bookmyshow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.project.bookmyshow.entity.Customer;
import edu.project.bookmyshow.repository.CustomerRepo;

@Repository
public class CustomerDao {

	@Autowired
	private CustomerRepo customerRepo;

	public Customer saveCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	public Customer deleteCustomer(long customerId) {
		Customer dbcustomer = getCustomerById(customerId);
		if (dbcustomer != null) {
			customerRepo.delete(dbcustomer);
			return dbcustomer;
		}
		return null;
	}

	public Customer getCustomerById(long customerId) {
		Optional<Customer> optional = customerRepo.findById(customerId);
		if (optional.isPresent()) {
			return customerRepo.findById(customerId).get();
		}
		return null;
	}

	public Customer updateCustomer(long customerId, Customer customer) {
		Optional<Customer> optional = customerRepo.findById(customerId);
		if (optional.isPresent()) {
			customer.setCustomerId(customerId);
			return customerRepo.save(customer);
		}
		return null;
	}
}
