package edu.project.bookmyshow.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.project.bookmyshow.entity.Address;
import edu.project.bookmyshow.repository.AddressRepo;

@Repository
public class AddressDao {

	@Autowired
	private AddressRepo addressRepo;
	
	public Address saveAddress(Address address) {
		return addressRepo.save(address);
	}
	
}
