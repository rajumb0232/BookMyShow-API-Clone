package edu.project.bookmyshow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.project.bookmyshow.entity.Screen;
import edu.project.bookmyshow.repository.ScreenRepo;

@Repository
public class ScreenDao {

	@Autowired
	private ScreenRepo screenRepo;
	
	public Screen getScreen(long screenId) {
		Optional<Screen> optional = screenRepo.findById(screenId);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
	}
}
