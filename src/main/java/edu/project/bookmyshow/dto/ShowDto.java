package edu.project.bookmyshow.dto;

import java.time.LocalTime;

import edu.project.bookmyshow.enums.ScreenAvailability;
import edu.project.bookmyshow.enums.ShowStatus;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ShowDto {
	private long showId;
	private LocalTime showTime;
	private ShowStatus showStatus;

	private ScreenAvailability screenAvailability;
	private double classicSeatPrice;
	private double goldSeatPrice;
	private double premiumSeatPrice;
}
