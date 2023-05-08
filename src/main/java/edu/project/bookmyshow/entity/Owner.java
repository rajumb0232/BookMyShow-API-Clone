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
import javax.validation.constraints.Pattern;


import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Owner {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ownerId;
	@Pattern(regexp = "[A-Z]{1}[a-z]*\\s*[a-z]*", message = "Name should start with capital letter and should not be given space in the begining")

	private String ownerName;
	@Min(6000000000l)
	@Max(9999999999l)
	private long ownerPhoneNumber;
	@Email(regexp = "[a-zA-Z0-9+_.-]+@[gmail]*+.[com]{3}", message = "invalid email--Should be in the extension of 'gmail.com' ")
	private String ownerEmail;
	@Pattern(regexp = "^(?=.*[0-9])+(?=.*[a-z])+(?=.*[A-Z])+(?=.*[@#$%^&+=])+(?=\\S+$).{8}$", message = "8 characters mandatory(1 upperCase,1 lowerCase,1 special Character,1 number)")
	private String ownerPassword;
	
	@OneToMany(mappedBy = "owner")
	private List<ProductionHouse> productionHouses;
	
	@OneToMany(mappedBy = "owner")
	private List<Theatre> theatres;
}
