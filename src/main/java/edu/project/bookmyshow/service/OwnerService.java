package edu.project.bookmyshow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.bookmyshow.dao.OwnerDao;
import edu.project.bookmyshow.dto.OwnerDto;
import edu.project.bookmyshow.entity.Owner;
import edu.project.bookmyshow.exception.OwnerNotFoundByIdException;
import edu.project.bookmyshow.util.ResponseStructure;

@Service
public class OwnerService {
	@Autowired
	private OwnerDao dao;

	public ResponseEntity<ResponseStructure<OwnerDto>> saveOwner(Owner owner) {
		Owner owner2 = dao.saveOwner(owner);
		if (owner2 != null) {
			OwnerDto dto = new OwnerDto();
			dto.setOwnerId(owner.getOwnerId());
			dto.setOwnerName(owner.getOwnerName());
			dto.setOwnerEmail(owner.getOwnerEmail());
			dto.setOwnerPhoneNumber(owner.getOwnerPhoneNumber());
			ResponseStructure<OwnerDto> structure = new ResponseStructure<OwnerDto>();
			structure.setMessage("Owner Saved Successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<OwnerDto>>(structure, HttpStatus.CREATED);
		}
		return null;
	}

	public ResponseEntity<ResponseStructure<OwnerDto>> getOwnerById(long id) {
		Owner owner = dao.getOwnerById(id);
		if (owner != null) {
			OwnerDto dto = new OwnerDto();
			dto.setOwnerId(owner.getOwnerId());
			dto.setOwnerName(owner.getOwnerName());
			dto.setOwnerEmail(owner.getOwnerEmail());
			dto.setOwnerPhoneNumber(owner.getOwnerPhoneNumber());
			ResponseStructure<OwnerDto> structure = new ResponseStructure<OwnerDto>();
			structure.setMessage("Owner fetched Successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<OwnerDto>>(structure, HttpStatus.FOUND);
		}
		else {
			throw new OwnerNotFoundByIdException("Failed to fetch Owner!!");
		}
	}

	public ResponseEntity<ResponseStructure<OwnerDto>> deleteOwnerById(long ownerId) {
		Owner owner = dao.deleteOwnerById(ownerId);
		if (owner != null) {
			OwnerDto dto = new OwnerDto();
			dto.setOwnerId(owner.getOwnerId());
			dto.setOwnerName(owner.getOwnerName());
			dto.setOwnerEmail(owner.getOwnerEmail());
			dto.setOwnerPhoneNumber(owner.getOwnerPhoneNumber());
			ResponseStructure<OwnerDto> structure = new ResponseStructure<OwnerDto>();
			structure.setMessage("Owner deleted Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<OwnerDto>>(structure, HttpStatus.OK);
		}

			throw new OwnerNotFoundByIdException("Failed to delete Owner!!");
		
	}

	public ResponseEntity<ResponseStructure<OwnerDto>> updateOwner(long ownerId, Owner owner2) {
		Owner owner = dao.updateOwner(ownerId, owner2);
		if (owner != null) {
			OwnerDto dto = new OwnerDto();
			dto.setOwnerId(owner.getOwnerId());
			dto.setOwnerName(owner.getOwnerName());
			dto.setOwnerEmail(owner.getOwnerEmail());
			dto.setOwnerPhoneNumber(owner.getOwnerPhoneNumber());
			ResponseStructure<OwnerDto> structure = new ResponseStructure<OwnerDto>();
			structure.setMessage("Owner updated Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<OwnerDto>>(structure, HttpStatus.OK);
		}
		else {
			throw new OwnerNotFoundByIdException("Failed to update Owner!!");
		}
	}

}
