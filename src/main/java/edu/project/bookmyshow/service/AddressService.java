package edu.project.bookmyshow.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.bookmyshow.dao.AddressDao;
import edu.project.bookmyshow.dto.AddressDto;
import edu.project.bookmyshow.entity.Address;
import edu.project.bookmyshow.util.ResponseStructure;

@Service
public class AddressService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AddressDao addressDao;

	public ResponseEntity<ResponseStructure<AddressDto>> saveAddress(AddressDto addressDto) {
		ResponseStructure<AddressDto> responseStructure = new ResponseStructure<>();
		
		Address address =(Address)this.modelMapper.map(addressDto, Address.class);
		//Address address = new Address();
		Address addressDto2 = addressDao.saveAddress(address);
		if (addressDto2 != null) {
			responseStructure.setMessage("address saved successfully");
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setData(addressDto2);
			return new ResponseEntity<ResponseStructure<AddressDto>>(responseStructure, HttpStatus.CREATED);
		}
		return null;
	}
}