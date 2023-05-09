package edu.project.bookmyshow.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long customerId;
	@NotBlank(message = "Customer cannot be blank")
	@NotNull(message = "Customer cannot be null")
	@Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]*", message = "Name should Start with capital letter")
	private String customerName;
	@Min(value =  6000000000l, message = "PhoneNumber should start with these digits[6,7,8,9]")
	@Max(value =  9999999999l, message = "PhoneNumber must be 10 digits")
	private long customrPhoneNumber;
	@NotBlank(message = "Customer cannot be blank")
	@NotNull(message = "Customer cannot be null")
	@Email(regexp = "[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}", message = "invalid email ")
	private String customerEmail;
	@NotBlank(message = "Customer cannot be blank")
	@NotNull(message = "Customer cannot be null")
	@Pattern(regexp = "(?=.*[0-9])+(?=.*[a-z])+(?=.*[A-Z])+(?=.*[@#$%^&+=])+(?=\\S+$).{8,}", message = "8 characters mandatory(1 upperCase,1 lowerCase,1 specialCharacter,1 number)")
	private String customerPassword;
	@OneToMany(mappedBy = "customer")
	private List<Ticket> tickets;
}
