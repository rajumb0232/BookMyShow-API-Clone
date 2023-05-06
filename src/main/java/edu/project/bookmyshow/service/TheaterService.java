package edu.project.bookmyshow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.bookmyshow.dao.OwnerDao;
import edu.project.bookmyshow.dao.TheaterDao;
import edu.project.bookmyshow.dto.TheatreDto;
import edu.project.bookmyshow.entity.Owner;
import edu.project.bookmyshow.entity.Theatre;
import edu.project.bookmyshow.exception.OwnerNotFoundByIdException;
import edu.project.bookmyshow.exception.TheaterNotFoundByIdException;
import edu.project.bookmyshow.util.ResponseStructure;

@Service
public class TheaterService {
	@Autowired
	private OwnerDao ownerDao;

	@Autowired
	private TheaterDao dao;

	public ResponseEntity<ResponseStructure<Theatre>> addTheatre(long ownerId, TheatreDto dto) {
		Owner owner = ownerDao.getOwnerById(ownerId);
		if (owner != null) {
			 Theatre theatre=new Theatre();
			theatre.setTheatreId(dto.getTheatreId());
			theatre.setTheatreName(dto.getTheatreName());
			theatre.setOwner(owner);
			ResponseStructure<Theatre> structure = new ResponseStructure<Theatre>();
			Theatre theatre2 = dao.addTheatre(theatre);
			if (theatre2 != null) {
				
				structure.setMessage("Theatre saved successfully");
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setData(theatre2);
				
			}
			return new ResponseEntity<ResponseStructure<Theatre>>(structure, HttpStatus.CREATED);
		} else {
//			owner id not found exception 
			throw new OwnerNotFoundByIdException("Failed to add Theatre!!");
		}
	}
	public ResponseEntity<ResponseStructure<Theatre>> updateTheatre(long theatreId,TheatreDto dto){
		 Theatre theatre=new Theatre();
		theatre.setTheatreName(dto.getTheatreName());
		Theatre theatre2=dao.updateTheatre(theatreId, theatre);
		if(theatre2!=null) {
			ResponseStructure<Theatre> structure = new ResponseStructure<Theatre>();
			structure.setMessage("Theatre updated successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(theatre2);
			return new ResponseEntity<ResponseStructure<Theatre>>(structure, HttpStatus.OK);
		}else {
//			throw theatreIdnOt found exception
			throw new TheaterNotFoundByIdException("Failed to update Theatre!!");
		}
	}
	
	public ResponseEntity<ResponseStructure<Theatre>> deleteTheatre(long theaterId){
		Theatre theatre1=dao.deleteTheatre(theaterId);
		if(theatre1!=null) {
			ResponseStructure<Theatre> structure = new ResponseStructure<Theatre>();
			structure.setMessage("Theatre deleted successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(theatre1);
			return new ResponseEntity<ResponseStructure<Theatre>>(structure, HttpStatus.FOUND);
		}else {
//			theatre id not found
			throw new TheaterNotFoundByIdException("Failed to delete Theatre!!");
		}
	}
	
	public ResponseEntity<ResponseStructure<Theatre>> findTheatre(long theaterId){
		Theatre theatre1=dao.getTheatreById(theaterId);
		if(theatre1!=null) {
			ResponseStructure<Theatre> structure = new ResponseStructure<Theatre>();
			structure.setMessage("Theatre fetched successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(theatre1);
			return new ResponseEntity<ResponseStructure<Theatre>>(structure, HttpStatus.FOUND);
		}else {
//			
			throw new TheaterNotFoundByIdException("Failed to fetch Theatre!!");
		}
	}
}
