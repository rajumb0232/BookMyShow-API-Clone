package edu.project.bookmyshow.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class AddressDto {
	private long addressId;
	@Min(value = 1,message = "FlatNo should be positive Number")
	@Max(value = 9999,message = "FlatNumber should not exceed 9999")
	private int flatNo;
	@NotBlank(message = "area cannot be blank")
	@NotNull(message = "area cannot be null")
	private String area;
	@NotBlank(message = "landmark cannot be blank")
	@NotNull(message = "landmark cannot be null")
	private String landmark;
	@NotBlank(message = "city cannot be blank")
	@NotNull(message = "city cannot be null")
	@Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]*", message = "CityName should Start with capital letter")
	private String city;
	@NotBlank(message = "state cannot be blank")
	@NotNull(message = "state cannot be null")
	@Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]*", message = "StateName should Start with capital letter")
	private String state;
	@NotBlank(message = "Country cannot be blank")
	@NotNull(message = "Country cannot be null")
	@Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]*", message = "CountryName should Start with capital letter")
	private String country;
}
