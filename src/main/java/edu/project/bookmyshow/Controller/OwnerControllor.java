package edu.project.bookmyshow.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.project.bookmyshow.dto.OwnerDto;
import edu.project.bookmyshow.entity.Owner;
import edu.project.bookmyshow.service.OwnerService;
import edu.project.bookmyshow.util.ResponseStructure;

@RestController
@RequestMapping("/owner")
public class OwnerControllor {

	@Autowired
	private OwnerService ownerService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<OwnerDto>> saveOwner(@RequestBody Owner owner){
		return ownerService.saveOwner(owner);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<OwnerDto>> getOwnerById(@RequestParam long ownerId){
		return ownerService.getOwnerById(ownerId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<OwnerDto>> deleteOwnerById(@RequestParam long ownerId){
		return ownerService.deleteOwnerById(ownerId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<OwnerDto>> updateOwner(@RequestParam long ownerId,@RequestBody Owner owner){
		return ownerService.updateOwner(ownerId,owner);
	}
	
	
	
	
}
