package edu.project.bookmyshow.entity;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import edu.project.bookmyshow.enums.Genre;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int movieId;
	private String movieName;
	private Genre genre1;
	private Genre genre2;
	private Genre genre3;
	private LocalTime movieDuration;
	private String movieDescription;
	private String language;
	
	@ManyToOne
	@JoinColumn
	private ProductionHouse productionHouse;
}
