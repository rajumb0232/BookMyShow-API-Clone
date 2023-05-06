package edu.project.bookmyshow.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import edu.project.bookmyshow.enums.SeatStatus;
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
	
	private long seatId;
	
	private String seatNumber;
	private SeatStatus seatStatus;
	private SeatType seatType;
	
	private double seatPrice;
}
