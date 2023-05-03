package edu.project.bookmyshow.dto;

import java.time.LocalTime;

import edu.project.bookmyshow.enums.Genre;
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
	
	private long movieId;
	private String movieNaame;
	private Genre genre;
	private LocalTime movieDuration;
	private String movieDescription;
	private String language;
	
	private long screenId;
	private String screenname;
	private ScreenAvailability screenAvailability;
	private double classicSeatPrice;
	private double goldSeatPrice;
	private double premiumSeatPrice;
}
