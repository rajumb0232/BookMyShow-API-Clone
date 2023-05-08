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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Owner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ownerId;
	// @Pattern(regexp = "[A-Z]{1}[a-z]*\\s*[A-Z]{1}[a-z]*\\s*[A-Z]{1}[a-z]*",
	// message = "Start with capital letter and should not give space in begining
	// and last")
	@NotBlank(message = "Owner cannot be blank")
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
	@NotBlank(message = "Owner cannot be blank")
	@NotNull(message = "Owner cannot be null")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "8 characters mandatory(1 upperCase,1 lowerCase,1 special Character,1 number)")
	private String ownerPassword;

	@OneToMany(mappedBy = "owner")
	@JsonIgnore
	private List<ProductionHouse> productionHouses;

	@OneToMany(mappedBy = "owner")
	@JsonIgnore
	private List<Theatre> theatres;
}
