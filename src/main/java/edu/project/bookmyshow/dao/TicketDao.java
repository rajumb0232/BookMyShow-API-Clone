package edu.project.bookmyshow.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.project.bookmyshow.entity.Ticket;
import edu.project.bookmyshow.repository.TicketRepo;

@Repository
public class TicketDao {

	@Autowired
	private TicketRepo ticketRepo;

	public Ticket bookTicket(Ticket ticket) {
		return ticketRepo.save(ticket);
	}
	
	
}
