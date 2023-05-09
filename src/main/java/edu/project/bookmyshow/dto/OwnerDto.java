package edu.project.bookmyshow.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class OwnerDto {
	private long ownerId;

	private String ownerName;

	private long ownerPhoneNumber;

	private String ownerEmail;

}
