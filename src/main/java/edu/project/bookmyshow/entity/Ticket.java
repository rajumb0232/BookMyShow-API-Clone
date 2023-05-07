package edu.project.bookmyshow.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.project.bookmyshow.enums.TicketStatus;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ticketId;
	private double totalPrice;
	private TicketStatus ticketStatus;
	
	@ManyToOne
	private Show show;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Booking> bookings;
	
	@ManyToOne 
	@JoinColumn
	@JsonIgnore
	private Customer customer;
}
