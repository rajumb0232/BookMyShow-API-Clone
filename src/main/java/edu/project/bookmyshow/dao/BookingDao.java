package edu.project.bookmyshow.dao;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.project.bookmyshow.entity.Booking;
import edu.project.bookmyshow.repository.BookingRepo;

@Repository
public class BookingDao {
	
	@Autowired
	private BookingRepo bookingRepo;
	
	public void saveBooking(Booking booking) {
		bookingRepo.save(booking);
	}

	public Booking getBookingByTime(LocalDateTime startTime, LocalDateTime endTime, long seatId) {
		Optional<Booking> optional = bookingRepo.getBookingByTime(startTime, endTime, seatId);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
	}
}
