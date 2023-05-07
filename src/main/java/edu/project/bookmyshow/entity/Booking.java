package edu.project.bookmyshow.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import edu.project.bookmyshow.enums.BookingStatus;
import edu.project.bookmyshow.enums.SeatType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long bookingId;
	private LocalDateTime bookedFromTime;
	private LocalDateTime bookedTillTime;
	private long seatId;
	private SeatType seatType;
	private BookingStatus bookingStatus;
	private double seatPrice;
}
