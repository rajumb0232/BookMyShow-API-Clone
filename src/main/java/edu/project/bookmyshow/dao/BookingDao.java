package edu.project.bookmyshow.dao;

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
}
