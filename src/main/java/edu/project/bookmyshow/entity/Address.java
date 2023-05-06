package edu.project.bookmyshow.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
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
	//@Pattern(regexp = "[09]{3}*")
	@Pattern(regexp = "[6,9][0-9]{9}", message = "invalid mobile number")
	private int flatNo; 
	private String area; 
	private String landmark; 
	private String city; 
	private String state;
	private String country;
	
	@OneToOne(mappedBy = "address")
	@JsonIgnore
	private Theatre theatre;
}
