package edu.project.bookmyshow.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.project.bookmyshow.enums.ShowStatus;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Show {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long showId;
	
	private LocalDateTime showStartTime;
	private LocalDateTime showEndTime;
	private ShowStatus showStatus;
	
	private String showLocation;
	
	private long movieId;
	
	private String movieName;
	private String genre;
	private LocalTime movieDuration;
	private String movieDescription;
	
	private String language;
	
	private long screenId;
	private String screenName;
  
	
	private double classicSeatPrice;
	
	private double goldSeatPrice;
	
	private double premiumSeatPrice;
	
	@ManyToOne 
	@JoinColumn
	@JsonIgnore
	private Theatre theatre;

	
}
