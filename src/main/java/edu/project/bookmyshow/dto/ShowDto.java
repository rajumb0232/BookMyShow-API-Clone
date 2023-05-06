package edu.project.bookmyshow.dto;

import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowDto {
	private long showId;
	@DateTimeFormat(style = "HH:mm")
	private LocalTime showTime;

	private double classicSeatPrice;
	private double goldSeatPrice;
	private double premiumSeatPrice;
}
