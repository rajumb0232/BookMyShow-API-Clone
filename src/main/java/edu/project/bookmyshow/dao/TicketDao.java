package edu.project.bookmyshow.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.project.bookmyshow.entity.Show;
import edu.project.bookmyshow.entity.Ticket;
import edu.project.bookmyshow.repository.TicketRepo;

@Repository
public class TicketDao {

	@Autowired
	private TicketRepo ticketRepo;

	public Ticket bookTicket(Ticket ticket) {
		return ticketRepo.save(ticket);
	}

	public List<Ticket> getTicketsByShow(Show show) {
		Optional<List<Ticket>> optional = ticketRepo.getTicketsByShow(show);
		if(optional.isEmpty()) {
			return new ArrayList<Ticket>();
		}else {
			return optional.get();
		}
	}

	public Ticket cancelTicket(Ticket ticket) {
		return ticketRepo.save(ticket);
	}

	public Ticket getTicket(long ticketId) {
		Optional<Ticket> optional = ticketRepo.findById(ticketId);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
	}
	
	
}
