package edu.project.bookmyshow.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
	private String area; 
	private String landmark; 
	@Pattern(regexp = "[A-Z]{1}[a-z]*")
	private String city; 
	@Pattern(regexp = "[A-Z]{1}[a-z]*")
	private String state;
	@Pattern(regexp = "[A-Z]{1}[a-z]*")
	private String country;
	
	@OneToOne(mappedBy = "address")
	@JsonIgnore
	private Theatre theatre;
}
