package edu.project.bookmyshow.dto;

import edu.project.bookmyshow.enums.SeatStatus;
import edu.project.bookmyshow.enums.SeatType;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class SeatDto {
	private long seatId;
	private String seatNumber;
	private SeatStatus seatStatus;
	private SeatType seatType;
}
