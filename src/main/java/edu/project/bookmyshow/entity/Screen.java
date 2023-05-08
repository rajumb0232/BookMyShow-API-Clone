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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
	@NotBlank(message = "screenName cannot be blank")
	@NotNull(message = "screenName cannot be null")
	@Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]*", message = "Name should Start with capital letter")
	private String screenName;
	
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
	private Theatre theatre ;
}
