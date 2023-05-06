package edu.project.bookmyshow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.project.bookmyshow.entity.Seat;
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

	public Seat addSeat(Seat seat) {
		return seatRepo.save(seat);
		
	}
}
