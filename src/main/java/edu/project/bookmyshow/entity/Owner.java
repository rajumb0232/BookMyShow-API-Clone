package edu.project.bookmyshow.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
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
	private String ownerName;
	//@Pattern(regexp = "[6,9][0-9]{9}", message = "invalid mobile number")
	private long ownerPhoneNumber;
	@Email(regexp = "[a-zA-Z0-9+_.-]+@[a-zA-Z]+\\.[a-z]{2,3}", message = "invalid email ")
	private String ownerEmail;
	
	private String ownerPassword;
	
	@OneToMany(mappedBy = "owner")
	private List<ProductionHouse> productionHouses;
	
	@OneToMany(mappedBy = "owner")
	private List<Theatre> theatres;
}
