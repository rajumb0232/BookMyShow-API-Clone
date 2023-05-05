package edu.project.bookmyshow.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.bookmyshow.dao.ScreenDao;
import edu.project.bookmyshow.dao.TheaterDao;
import edu.project.bookmyshow.dto.ScreenDto;
import edu.project.bookmyshow.dto.TheatreDto;
import edu.project.bookmyshow.entity.Address;
import edu.project.bookmyshow.entity.Screen;
import edu.project.bookmyshow.entity.Theatre;
import edu.project.bookmyshow.util.ResponseStructure;

@Service
public class ScreenService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ScreenDao screenDao;

	@Autowired
	private TheaterDao theaterDao;

	public ResponseEntity<ResponseStructure<ScreenDto>> saveScreen(long theatreId, ScreenDto screenDto) {
		ResponseStructure<ScreenDto> responseStructure = new ResponseStructure<>();
		Theatre theatre = theaterDao.getTheatreById(theatreId);
		if (theatre != null) {
			Screen screen = (Screen) this.modelMapper.map(screenDto, Screen.class);
			if (screen != null) {
				screen = screenDao.saveScreen(screen);
				List<Screen> list = new ArrayList<>();
				//list.add(screenDto);
				list.addAll(theatre.getScreens());
				theatre.setScreens(list);
				theaterDao.updateTheatre(theatreId, theatre);

			}
		}

		return null;
	}

}
