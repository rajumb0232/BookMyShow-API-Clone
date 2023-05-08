package edu.project.bookmyshow.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import edu.project.bookmyshow.enums.ScreenType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScreenDto {
	private long screenId;
	@NotBlank(message = "screenName cannot be blank")
	@NotNull(message = "screenName cannot be null")
	@Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]*", message = "Name should Start with capital letter")
	private String screenName;
	private ScreenType screenType;
	private int numberOfClassicSeat;
	private int numberOfGoldSeat;
	private int numberOfPlatinumSeat;
}
