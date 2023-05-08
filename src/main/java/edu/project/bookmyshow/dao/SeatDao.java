package edu.project.bookmyshow.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.project.bookmyshow.entity.Screen;
import edu.project.bookmyshow.entity.Seat;
import edu.project.bookmyshow.enums.SeatType;
import edu.project.bookmyshow.repository.SeatRepo;

@Repository
public class SeatDao {

	@Autowired
	private SeatRepo seatRepo;
	
	public Seat getSeat(long seatId) {
		Optional<Seat> optional = seatRepo.findById(seatId);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
	}

	public void updateSeat(Seat seat) {
		seatRepo.save(seat);
	}

	public List<Seat> getSeatsByStatusByScreen(SeatType seatType, Screen screen) {
		Optional<List<Seat>> optional = seatRepo.getSeatsByStatusByScreen(seatType, screen);
		if(optional.isEmpty()) {
			return new ArrayList<Seat>();
		}else {
			return optional.get();
		}
	}
	
	public Seat addSeat(Seat seat) {
		return seatRepo.save(seat);
		
	}

	public void removeSeat(long seatId) {
		seatRepo.deleteById(seatId);
	}
}
