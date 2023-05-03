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
	public Customer deleteCustomer(long id) {
		Customer dbcustomer=getCustomerById(id);
		if(dbcustomer!=null) {
			customerRepo.delete(dbcustomer);
			return dbcustomer;
		}
		return null;
	}
	public Customer getCustomerById(long id) {
		Optional<Customer>optional=customerRepo.findById(id);
		if(optional.isPresent()) {
			return customerRepo.findById(id).get();
		}
		return null;
	}
	public Customer updateCustomer(long id, Customer customer) {
		Optional<Customer> optional=customerRepo.findById(id);
		if(optional.isPresent()) {
			customer.setCustomerId(id);
		return customerRepo.save(customer);
		}
		return null;
	}
}
