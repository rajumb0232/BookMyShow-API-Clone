package edu.project.bookmyshow.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long customerId;
	private String customerName;
	private long customrPhoneNumber;
	private String customerEmail;
	private String customerPassword;

	@OneToMany(mappedBy = "customer")
	private List<Ticket> tickets;
}
