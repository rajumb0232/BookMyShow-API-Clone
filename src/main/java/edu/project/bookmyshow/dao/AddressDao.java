package edu.project.bookmyshow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.project.bookmyshow.entity.Address;
import edu.project.bookmyshow.entity.Theatre;
import edu.project.bookmyshow.repository.AddressRepo;

@Repository
public class AddressDao {

	@Autowired
	private AddressRepo addressRepo;

	public Address saveAddress(Address address) {
		return addressRepo.save(address);
	}

	public Address deleteAddress(long addressId) {
		Address dbAddress = getAddressById(addressId);
		if (dbAddress != null) {
			addressRepo.delete(dbAddress);
			return dbAddress;
		}
		return null;
	}

	public Address getAddressById(long addressId) {
		Optional<Address> optional = addressRepo.findById(addressId);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public Address updateAddress(long addressId, Address address) {
		Optional<Address> optional = addressRepo.findById(addressId);
		if (optional.isPresent()) {
			address.setAddressId(addressId);
			return addressRepo.save(address);
		}
		return null;
	}
	public List<Theatre> getAddressByCity(String city) {
		if( addressRepo.getTheatresBYCity(city).isEmpty()) {
			return null;
		}
		return addressRepo.getTheatresBYCity(city);
	}
}
