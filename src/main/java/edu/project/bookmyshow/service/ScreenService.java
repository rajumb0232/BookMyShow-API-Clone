package edu.project.bookmyshow.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.bookmyshow.dao.ScreenDao;
import edu.project.bookmyshow.dao.TheaterDao;
import edu.project.bookmyshow.dto.ScreenDto;
import edu.project.bookmyshow.entity.Screen;
import edu.project.bookmyshow.entity.Theatre;
import edu.project.bookmyshow.exception.ScreenNotFoundByIdException;
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
			screen.setTheatre(theatre);
			screen = screenDao.saveScreen(screen);
			theaterDao.updateTheatre(theatreId, theatre);
			responseStructure.setMessage("address saved successfully");
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setData(screen);
			return new ResponseEntity<ResponseStructure<ScreenDto>>(responseStructure, HttpStatus.CREATED);
		}
		throw new ScreenNotFoundByIdException("Failed to add Screen!!");
// SharathVeda
	}

	public ResponseEntity<ResponseStructure<ScreenDto>> updateScreen(long screenId, ScreenDto screenDto) {
		ResponseStructure<ScreenDto> responseStructure = new ResponseStructure<>();
		Screen screen = this.modelMapper.map(screenDto, Screen.class);
		screen = screenDao.updateScreen(screenId, screen);
		if (screen != null) {
			responseStructure.setMessage("address updated successfully");
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setData(screen);
			return new ResponseEntity<ResponseStructure<ScreenDto>>(responseStructure, HttpStatus.OK);
		}
		throw new ScreenNotFoundByIdException("Failed to add Screen!!");
	}

	public ResponseEntity<ResponseStructure<ScreenDto>> deleteScreen(long screenId) {
		ResponseStructure<ScreenDto> responseStructure = new ResponseStructure<>();
		Screen screen = screenDao.deleteScreen(screenId);
		if (screen != null) {
			responseStructure.setMessage("address deleted successfully");
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setData(screen);
			return new ResponseEntity<ResponseStructure<ScreenDto>>(responseStructure, HttpStatus.OK);
		}
		throw new ScreenNotFoundByIdException("Failed to add Screen!!");
	}

	public ResponseEntity<ResponseStructure<ScreenDto>> getScreenById(long screenId) {
		ResponseStructure<ScreenDto> responseStructure = new ResponseStructure<>();
		Screen screen = screenDao.getScreenById(screenId);
		if (screen != null) {
			responseStructure.setMessage("address fetched successfully");
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setData(screen);
			return new ResponseEntity<ResponseStructure<ScreenDto>>(responseStructure, HttpStatus.FOUND);
		}
		throw new ScreenNotFoundByIdException("Failed to add Screen!!");
	}
}
