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

	public Screen getScreenById(long screenId) {
		Optional<Screen> optional = screenRepo.findById(screenId);
		if (optional.isEmpty()) {
			return null;
		} else {
			return optional.get();
		}
	}

	public Screen saveScreen(Screen screen) {
		return screenRepo.save(screen);
	}

	public Screen updateScreen(long screenId, Screen screen) {
		Optional<Screen> optional = screenRepo.findById(screenId);
		if (optional.isPresent()) {
			
			screen.setScreenId(screenId);
			screen.setSeats(optional.get().getSeats());
			screen.setTheatre(optional.get().getTheatre());
			return screenRepo.save(screen);
		}
		return null;
	}

	public Screen deleteScreen(long screenId) {
		Screen screen = getScreenById(screenId);
		if (screen != null) {
			screenRepo.delete(screen);
			return screen;
		}
		return null;
	}

	public Screen cancelShow(Screen screen) {
		return screenRepo.save(screen);
	}
}
