package edu.project.bookmyshow.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class OwnerDto {
	private long ownerId;	@NotBlank(message = "Owner cannot be blank")
	@NotNull(message = "Owner cannot be null")
	@Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]*", message = "Name should Start with capital letter")
	// @Pattern(regexp = "[a-zA-Z\\s]*")
	private String ownerName;
	@Min(6000000000l)
	@Max(9999999999l)
	private long ownerPhoneNumber;
	@NotBlank(message = "Owner cannot be blank")
	@NotNull(message = "Owner cannot be null")
	@Email(regexp = "[a-zA-Z0-9+_.-]+@[g][m][a][i][l]+.[c][o][m]", message = "invalid email--Should be in the extension of '@gmail.com' ")
	private String ownerEmail;
	
}
