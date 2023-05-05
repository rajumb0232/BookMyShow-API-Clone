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
//			Screen screen =new Screen();
//		
//			screen.setScreenName(screenDto.getScreenName());
//			screen.setScreenType(screenDto.getScreenType());
//			screen.setNumberOfClassicSeat(screenDto.getNumberOfClassicSeat());
//			screen.setNumberOfGoldSeat(screenDto.getNumberOfGoldSeat());
//			screen.setNumberOfPlatinumSeat(screen.getNumberOfPlatinumSeat());
			//	System.out.println(screen.getScreenName());
//				List<Screen> list = new ArrayList<>();
//				list.add(screen);
//				list.addAll(theatre.getScreens());
				//theatre.getScreens().add(screen);
				screen.setTheatre(theatre);
				screen = screenDao.saveScreen(screen);
				theaterDao.updateTheatre(theatreId, theatre);
				responseStructure.setMessage("address saved successfully");
				responseStructure.setStatus(HttpStatus.CREATED.value());
				responseStructure.setData(screen);
				return new ResponseEntity<ResponseStructure<ScreenDto>>(responseStructure, HttpStatus.CREATED);
			}
		throw new ScreenNotFoundByIdException("Failed to add Screen!!");

	}

}
