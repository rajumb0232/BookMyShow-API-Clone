package edu.project.bookmyshow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.project.bookmyshow.entity.Theatre;
import edu.project.bookmyshow.repository.TheatreRepo;

@Repository
public class TheaterDao {
	
	@Autowired
	private TheatreRepo repo;
	public Theatre addTheatre(Theatre theatre) {
		return repo.save(theatre);
	}

	public Theatre updateTheatre(long theaterId,Theatre theatre) {
		Optional<Theatre> optional=repo.findById(theaterId);
		if(optional.isPresent()) {
			theatre.setTheatreId(theaterId);
			return repo.save(theatre);
		}
		return null;
	}
	public Theatre deleteTheatre(long theatreId) {
		Optional<Theatre> optional=repo.findById(theatreId);
		if(optional.isPresent()) {
		
			Theatre theatre=optional.get();
			theatre.setScreens(null);
			theatre.setShows(null);
			 repo.delete(theatre);
			 return optional.get();
		}
		return null;
	}
	public Theatre getTheatreById(long theatreId) {
		Optional<Theatre> optional=repo.findById(theatreId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
}
