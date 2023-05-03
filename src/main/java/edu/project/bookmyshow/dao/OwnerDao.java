package edu.project.bookmyshow.dao;

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
}
