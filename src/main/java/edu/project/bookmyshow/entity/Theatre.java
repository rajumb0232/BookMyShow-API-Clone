package edu.project.bookmyshow.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Theatre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long theatreId;
	@NotBlank(message = "theatreName cannot be blank")
	@NotNull(message = "theatreName cannot be null")
	@Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]*", message = "Name should Start with capital letter")
	private String theatreName;
	
	@OneToOne 
	@JoinColumn
	private Address address;
	
	@OneToMany(mappedBy = "theatre",fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Screen> screens;
	
	@ManyToOne 
	@JoinColumn
	@JsonIgnore
	private Owner owner;
	
	@OneToMany(mappedBy = "theatre")
	@JsonIgnore
	private List<Show> shows;

}
