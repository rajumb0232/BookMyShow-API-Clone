package edu.project.bookmyshow.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.project.bookmyshow.entity.Booking;

public interface BookingRepo  extends JpaRepository<Booking, Long>{
	
	@Query(value = "select b from Booking b where b.seatId=?1")
	public Optional<Booking> getBookingBySeatId(Long id);
	
	@Query(value = "select b from Booking b where b.bookedFromTime=?1 and b.bookedTillTime=?2 and b.seatId=?3")
	public Optional<Booking> getBookingByTime(LocalDateTime startTime, LocalDateTime endTime, long seatId);
	
}
