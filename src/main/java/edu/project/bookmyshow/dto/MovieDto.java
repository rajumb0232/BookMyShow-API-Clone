package edu.project.bookmyshow.dto;

import java.time.LocalTime;

import edu.project.bookmyshow.enums.Genre;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class MovieDto {
	private int movieId;
	private String movieName;
	private Genre genre1;
	private Genre genre2;
	private Genre genre3;
	private LocalTime movieDuration;
	private String movieDescription;
	private String language;
}
