package edu.project.bookmyshow.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	private SeatType seatType;
	
	@ManyToOne
	@JoinColumn
	private Screen screen;

}
