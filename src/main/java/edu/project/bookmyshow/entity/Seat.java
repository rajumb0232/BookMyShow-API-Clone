package edu.project.bookmyshow.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import edu.project.bookmyshow.enums.SeatStatus;
import edu.project.bookmyshow.enums.SeatType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Seat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long seatId;
	private String seatNumber;
	private SeatStatus seatStatus;
	private SeatType seatType;
	
	@OneToMany(mappedBy = "seat")
	private List<Booking> booking; 
	
	@ManyToOne
	@JoinColumn
	private Screen screen;

}
