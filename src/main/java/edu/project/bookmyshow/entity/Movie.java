package edu.project.bookmyshow.entity;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.project.bookmyshow.enums.Genre;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long movieId;
	@Pattern(regexp = "[A-Z]{1}[a-z]*\\s*[A-Z]{1}[a-z]*\\s*[A-Z]{1}[a-z]*", message = "Name should start with capital letter and should not be given space in the begining and last")
	private String movieName;
	private Genre genre1;
	private Genre genre2;
	private Genre genre3;
	@DateTimeFormat(style = "HH:mm")
	private LocalTime movieDuration;
	private String movieDescription;
	@Pattern(regexp = "[A-Z]{1}[a-z]*", message = "Name should start with Capital Letter")
	private String language;

	@ManyToOne
	@JoinColumn
	@JsonIgnore
	private ProductionHouse productionHouse;
}
