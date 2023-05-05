package edu.project.bookmyshow.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.bookmyshow.dao.AddressDao;
import edu.project.bookmyshow.dao.TheaterDao;
import edu.project.bookmyshow.dto.AddressDto;
import edu.project.bookmyshow.entity.Address;
import edu.project.bookmyshow.entity.Theatre;
import edu.project.bookmyshow.exception.AddressNotFoundByIdException;
import edu.project.bookmyshow.exception.TheaterNotFoundByIdException;
import edu.project.bookmyshow.util.ResponseStructure;

@Service
public class AddressService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AddressDao addressDao;

	@Autowired
	private TheaterDao theaterDao;

	public ResponseEntity<ResponseStructure<AddressDto>> saveAddress(AddressDto addressDto, long theatreId) {
		ResponseStructure<AddressDto> responseStructure = new ResponseStructure<>();
		Theatre theatre = theaterDao.getTheatreById(theatreId);
		if (theatre != null) {
			Address address = (Address) this.modelMapper.map(addressDto, Address.class);
			if (address != null) {
				theatre.setAddress(address);
				address = addressDao.saveAddress(address);
				theaterDao.updateTheatre(theatreId, theatre);
				responseStructure.setMessage("address saved successfully");
				responseStructure.setStatus(HttpStatus.CREATED.value());
				responseStructure.setData(address);
				return new ResponseEntity<ResponseStructure<AddressDto>>(responseStructure, HttpStatus.CREATED);
			}
			throw new AddressNotFoundByIdException("Failed to add Address!!");
		} else {
			throw new TheaterNotFoundByIdException("Failed to add Address!!");
		}
	}

	public ResponseEntity<ResponseStructure<AddressDto>> deleteAddress(long addressId) {
		ResponseStructure<AddressDto> responseStructure = new ResponseStructure<>();
		Address dbAddress = addressDao.deleteAddress(addressId);
		if (dbAddress != null) {
			responseStructure.setMessage("address deleted successfully");
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setData(dbAddress);
			return new ResponseEntity<ResponseStructure<AddressDto>>(responseStructure, HttpStatus.OK);
		}
		throw new AddressNotFoundByIdException("Failed to add Address!!");
	}

	public ResponseEntity<ResponseStructure<AddressDto>> updateAddress(long addressId, AddressDto addressDto) {
		ResponseStructure<AddressDto> responseStructure = new ResponseStructure<>();
		Address address = this.modelMapper.map(addressDto, Address.class);
		address = addressDao.updateAddress(addressId, address);
		if (address != null) {
			responseStructure.setMessage("address updated successfully");
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setData(address);
			return new ResponseEntity<ResponseStructure<AddressDto>>(responseStructure, HttpStatus.CREATED);
		}
		throw new AddressNotFoundByIdException("Failed to add Address!!");
	}

	public ResponseEntity<ResponseStructure<AddressDto>> getAddressById(long addressId) {
		ResponseStructure<AddressDto> responseStructure = new ResponseStructure<>();
		Address address = addressDao.getAddressById(addressId);
		if (address != null) {
			responseStructure.setMessage("address fetched successfully");
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setData(address);
			return new ResponseEntity<ResponseStructure<AddressDto>>(responseStructure, HttpStatus.CREATED);
		}
		throw new AddressNotFoundByIdException("Failed to add Address!!");
	}

}