package edu.project.bookmyshow.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TheatreDto {
	private long theatreId;
	@NotBlank(message = "theatreName cannot be blank")
	@NotNull(message = "theatreName cannot be null")
	@Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]*", message = "Name should Start with capital letter")
	private String theatreName;
}
