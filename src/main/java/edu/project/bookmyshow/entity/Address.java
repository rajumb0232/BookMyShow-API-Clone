package edu.project.bookmyshow.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long addressId; 
	@Min(1000)
	@Max(9999)
	private int flatNo; 
	@NotBlank(message = "Address cannot be blank")
	@NotNull(message = "Address cannot be null")
	private String area; 
	@NotBlank(message = "Address cannot be blank")
	@NotNull(message = "Address cannot be null")
	private String landmark; 
	@NotBlank(message = "Address cannot be blank")
	@NotNull(message = "Address cannot be null")
	@Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]*", message = "Name should Start with capital letter")
	private String city; 
	@NotBlank(message = "Address cannot be blank")
	@NotNull(message = "Address cannot be null")
	@Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]*", message = "Name should Start with capital letter")
	private String state;
	@NotBlank(message = "Address cannot be blank")
	@NotNull(message = "Address cannot be null")
	@Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]*", message = "Name should Start with capital letter")
	private String country;
	
	@OneToOne(mappedBy = "address")
	@JsonIgnore
	private Theatre theatre;
}
