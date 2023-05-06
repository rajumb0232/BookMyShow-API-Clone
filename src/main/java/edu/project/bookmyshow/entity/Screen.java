package edu.project.bookmyshow.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.project.bookmyshow.enums.ScreenAvailability;
import edu.project.bookmyshow.enums.ScreenType;
import edu.project.bookmyshow.enums.Screenstatus;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Screen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long screenId;
	@Pattern(regexp = "[A-Z]{1}[a-z]*\\s*[A-Z]{1}[a-z]*\\s*[A-Z]{1}[a-z]*", message = "Start with capital letter and should not give space in begining and last")
	private String screenName;
	@Pattern(regexp = "[A-Z]{1}[a-z]*",message = "Name should start with Capital Letter")
	private ScreenType screenType;
	private ScreenAvailability screenAvailability;
	private Screenstatus screenstatus;
	
	@OneToMany(mappedBy = "screen", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Seat> seats;
	
	private int numberOfClassicSeat;
	private int numberOfGoldSeat;
	private int numberOfPlatinumSeat;
	
	@ManyToOne
	@JoinColumn
	@JsonIgnore
	private Theatre theatre;
}
