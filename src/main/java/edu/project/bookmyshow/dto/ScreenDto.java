package edu.project.bookmyshow.dto;

import edu.project.bookmyshow.enums.ScreenType;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ScreenDto {
	private long screenId;
	private String screenName;
	private ScreenType screenType;
	private int umberOfClassicSeat;
	private int numberOfGoldSeat;
	private int numberOfPlatinumSeat;
}
