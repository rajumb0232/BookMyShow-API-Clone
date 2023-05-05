package edu.project.bookmyshow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.project.bookmyshow.entity.Owner;
import edu.project.bookmyshow.repository.OwnerRepo;

@Repository
public class OwnerDao {

	@Autowired
	private OwnerRepo repo;
	public Owner saveOwner(Owner owner) {
	return repo.save(owner);
	}
	
	public Owner getOwnerById(long id) {
	Optional<Owner> optional=repo.findById(id);
	if(optional.isPresent()) {
		return optional.get();
	}
	return null;
	}

	public Owner deleteOwnerById(long ownerId) {
		Optional<Owner> optional=repo.findById(ownerId);
		if(optional.isPresent()) {
			repo.deleteById(ownerId);
			return optional.get();
		}
		return null;
	}

	public Owner updateOwner(long ownerId, Owner owner) {
		Optional<Owner> optional=repo.findById(ownerId);
		if(optional.isPresent()) {
			owner.setOwnerId(ownerId);
			return repo.save(owner);
		}
		return null;
	}
	
}
