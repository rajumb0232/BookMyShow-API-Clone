package edu.project.bookmyshow.dao;

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
	
}
