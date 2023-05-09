package edu.project.bookmyshow.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class CustomerDto {
	private long customerId;
	
	private String customerName;

	private long customerPhoneNumber;
	
	private String customerEmail;
}
