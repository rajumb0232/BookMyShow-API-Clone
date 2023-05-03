package edu.project.bookmyshow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
	private long addressId; 
	private int flatNo; 
	private String area; 
	private String landmark; 
	private String city; 
	private String state;
	private String country;
}
