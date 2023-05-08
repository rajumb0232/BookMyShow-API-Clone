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
	/**
	 * get the movie duration in the string format,
	 * split with : and add each hour, minutes and seconds to the local date time
	 * and then update the show end time automatically,*/
	private LocalTime movieDuration;
	private String movieDescription;
	private String language;
}
