package edu.project.bookmyshow.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowDto {
	private long showId;
	private LocalDateTime showStartTime;
	private LocalDateTime showEndTime;
	@Min(value = 1, message = "Minimum price for ClassicSeat should be 1")
	@Max(value = 9999, message = "Maximum price for ClassicSeat should be 9999")
	private double classicSeatPrice;
	@Min(value = 1, message = "Minimum price for ClassicSeat should be 1")
	@Max(value = 9999, message = "Maximum price for ClassicSeat should be 9999")
	private double goldSeatPrice;
	@Min(value = 1, message = "Minimum price for ClassicSeat should be 1")
	@Max(value = 9999, message = "Maximum price for ClassicSeat should be 9999")
	private double premiumSeatPrice;
}
