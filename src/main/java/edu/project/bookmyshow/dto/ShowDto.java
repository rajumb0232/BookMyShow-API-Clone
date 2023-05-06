package edu.project.bookmyshow.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowDto {
	private long showId;
	private LocalDateTime showStartTime;
	private LocalDateTime showEndTime;

	private double classicSeatPrice;
	private double goldSeatPrice;
	private double premiumSeatPrice;
}
