package edu.project.bookmyshow.dto;

import java.time.LocalTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import edu.project.bookmyshow.enums.Genre;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class MovieDto {
	private int movieId;
	@NotBlank(message = "Movie cannot be blank")
	@NotNull(message = "Movie cannot be null")
	@Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]*", message = "Name should Start with capital letter")
	private String movieName;
	private Genre genre1;
	private Genre genre2;
	private Genre genre3;
	@DateTimeFormat(style = "HH:mm")
	private LocalTime movieDuration;
	private String movieDescription;
	@NotBlank(message = "Movie cannot be blank")
	@NotNull(message = "Movie cannot be null")
	@Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]*", message = "Name should Start with capital letter")
	private String language;

}
