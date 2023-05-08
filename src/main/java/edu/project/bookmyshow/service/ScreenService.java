package edu.project.bookmyshow.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.bookmyshow.dao.ScreenDao;
import edu.project.bookmyshow.dao.SeatDao;
import edu.project.bookmyshow.dao.TheaterDao;
import edu.project.bookmyshow.dto.ScreenDto;
import edu.project.bookmyshow.entity.Screen;
import edu.project.bookmyshow.entity.Seat;
import edu.project.bookmyshow.entity.Theatre;
import edu.project.bookmyshow.enums.ScreenAvailability;
import edu.project.bookmyshow.enums.Screenstatus;
import edu.project.bookmyshow.enums.SeatType;
import edu.project.bookmyshow.exception.ScreenNotFoundByIdException;
import edu.project.bookmyshow.exception.TheaterNotFoundByIdException;
import edu.project.bookmyshow.util.ResponseStructure;

@Service
public class ScreenService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ScreenDao screenDao;

	@Autowired
	private TheaterDao theaterDao;
	@Autowired
	private SeatDao seatDao;

	public ResponseEntity<ResponseStructure<ScreenDto>> saveScreen(long theatreId, ScreenDto screenDto) {
		ResponseStructure<ScreenDto> responseStructure = new ResponseStructure<>();
		Theatre theatre = theaterDao.getTheatreById(theatreId);
		if (theatre != null) {
			Screen screen = (Screen) this.modelMapper.map(screenDto, Screen.class);
			List<Seat> seats = new ArrayList<>();

			for (int c = screen.getNumberOfClassicSeat(); c > 0; c--) {
				Seat seat = new Seat();
				seat.setSeatType(SeatType.CLASSIC);
				seat.setScreen(screen);
				seats.add(seat);
			}
			for (int g = screen.getNumberOfGoldSeat(); g > 0; g--) {
				Seat seat = new Seat();
				seat.setSeatType(SeatType.GOLD);
				seat.setScreen(screen);
				seats.add(seat);
			}
			for (int p = screen.getNumberOfPlatinumSeat(); p > 0; p--) {
				Seat seat = new Seat();
				seat.setSeatType(SeatType.PREMIUM);
				seat.setScreen(screen);
				seats.add(seat);
			}
			screen.setSeats(seats);
			screen.setTheatre(theatre);
			screen.setScreenAvailability(ScreenAvailability.NOT_ALLOTTED);
			screen.setScreenstatus(Screenstatus.AVAILABLE);
			screen = screenDao.saveScreen(screen);
			theaterDao.updateTheatre(theatreId, theatre);
			responseStructure.setMessage("screen saved successfully");
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setData(screen);
			return new ResponseEntity<ResponseStructure<ScreenDto>>(responseStructure, HttpStatus.CREATED);
		}
		throw new TheaterNotFoundByIdException("Failed to add Theater!!");
	}

	
	/**
	 * have to make sure if the user is trying to update the number of seats in the
	 * screen, if yes add those requested seats (can be done by subtracting the
	 * initial seat number with requested seat number and add only those requested.)
	 */
	public ResponseEntity<ResponseStructure<ScreenDto>> updateScreen(long screenId, ScreenDto screenDto) {
		ResponseStructure<ScreenDto> responseStructure = new ResponseStructure<>();
		Screen exScreen = screenDao.getScreenById(screenId);
		if(exScreen!=null) {
			Screen screen = (Screen) this.modelMapper.map(screenDto, Screen.class);
			screen.setScreenAvailability(exScreen.getScreenAvailability());
			Theatre theatre = exScreen.getTheatre();
			screen.setScreenId(screenId);
			screen.setTheatre(theatre);
			List<Seat> seats = exScreen.getSeats();
			
			List<Seat> exClassicSeats = seatDao.getSeatsByStatusByScreen(SeatType.CLASSIC, screen);
			int classicSeats = exClassicSeats.size() - screen.getNumberOfClassicSeat();
			System.err.println(classicSeats);
			List<Seat> exGoldSeats = seatDao.getSeatsByStatusByScreen(SeatType.GOLD, screen);
			int goldSeats = exGoldSeats.size() - screen.getNumberOfGoldSeat();
			System.err.println(goldSeats);
			List<Seat> exPremiumSeats = seatDao.getSeatsByStatusByScreen(SeatType.PREMIUM, screen);
			int premiumSeats = exPremiumSeats.size() - screen.getNumberOfPlatinumSeat();
			System.err.println(premiumSeats);
			if(classicSeats<0) {
				for (int c = classicSeats; c<0; c++) {
					Seat seat = new Seat();
					seat.setSeatType(SeatType.CLASSIC);
					seat.setScreen(screen);
					seats.add(seat);
				}
			}else if (classicSeats>0){
				/*
				 * finding the list of seats based on type, then removing the seats
				 * by iterating over their negative difference, for the condition 
				 * difference < 0*/
				
				for(Seat seat : exClassicSeats ) {
					if(classicSeats>0) {
						seatDao.removeSeat(seat.getSeatId());
						classicSeats--;
					}else {
						break;
					}
				}
			}
			
			if(goldSeats<0) {
				for (int g = screen.getNumberOfGoldSeat(); g < 0; g++) {
					Seat seat = new Seat();
					seat.setSeatType(SeatType.GOLD);
					seat.setScreen(screen);
					seats.add(seat);
				}
			}else if(goldSeats>0){
				for(Seat seat : exGoldSeats ) {
					if(goldSeats>0) {
						seatDao.removeSeat(seat.getSeatId());
						goldSeats--;
					}else {
						break;
					}
				}
			}

			if(premiumSeats<0) {
				for (int p = screen.getNumberOfPlatinumSeat(); p<0; p++) {
					Seat seat = new Seat();
					seat.setSeatType(SeatType.PREMIUM);
					seat.setScreen(screen);
					seats.add(seat);
				}
			}else if (premiumSeats>0){
				for(Seat seat : exPremiumSeats ) {
					if(premiumSeats>0) {
						seatDao.removeSeat(seat.getSeatId());
						premiumSeats--;
					}else {
						break;
					}
				}
			}
			

			screen.setSeats(seats);
			screen.setTheatre(theatre);
			screen = screenDao.saveScreen(screen);
			theaterDao.addTheatre(theatre);
			responseStructure.setMessage("screen updated successfully");
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setData(screen);
			return new ResponseEntity<ResponseStructure<ScreenDto>>(responseStructure, HttpStatus.OK);
		}else
			throw new ScreenNotFoundByIdException("Failed to Update Screen!!");

	}

	
	public ResponseEntity<ResponseStructure<ScreenDto>> deleteScreen(long screenId) {
		ResponseStructure<ScreenDto> responseStructure = new ResponseStructure<>();
		Screen screen = screenDao.deleteScreen(screenId);
		if (screen != null) {
			responseStructure.setMessage("Screen deleted successfully");
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
			responseStructure.setMessage("screen fetched successfully");
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setData(screen);
			return new ResponseEntity<ResponseStructure<ScreenDto>>(responseStructure, HttpStatus.FOUND);
		}
		throw new ScreenNotFoundByIdException("Failed to add Screen!!");
	}
}
