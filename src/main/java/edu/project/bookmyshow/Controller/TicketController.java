package edu.project.bookmyshow.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.project.bookmyshow.dto.TicketDto;
import edu.project.bookmyshow.entity.Ticket;
import edu.project.bookmyshow.service.TicketService;
import edu.project.bookmyshow.util.ResponseStructure;

@RestController
@RequestMapping("/ticket")
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Ticket>> bookTicket(@RequestBody TicketDto ticketDto,
			@RequestParam long customerId, @RequestParam long showId, @RequestParam Long[] seatId){
		return ticketService.bookTicket(ticketDto, customerId, showId, seatId);
	}
	
	@GetMapping
	public Integer[] test(@RequestParam Integer[] seatId) {
		return seatId;
	}
}
