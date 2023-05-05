package edu.project.bookmyshow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.project.bookmyshow.entity.Show;
import edu.project.bookmyshow.repository.ShowRepo;

@Service
public class ShowDao {
	
	@Autowired
	private ShowRepo showRepo;
	
	public Show addShow(Show show) {
		return showRepo.save(show);
	}

	public Show getShow(long showId) {
		Optional<Show> optional = showRepo.findById(showId);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
	}
}
