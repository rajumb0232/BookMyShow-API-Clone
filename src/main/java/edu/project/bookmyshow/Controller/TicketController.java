package edu.project.bookmyshow.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.project.bookmyshow.entity.Ticket;
import edu.project.bookmyshow.service.TicketService;
import edu.project.bookmyshow.util.ResponseStructure;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/ticket")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@ApiOperation(value = "Save Ticket", notes = " Api is used to save the Ticket")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created"),
			@ApiResponse(code = 404, message = "Ticket not found for the given  id") })
	@PostMapping
	public ResponseEntity<ResponseStructure<Ticket>> bookTicket(@RequestParam long customerId,
			@RequestParam long showId, @RequestParam Long[] seatId) {

		return ticketService.bookTicket(customerId, showId, seatId);
	}

	@ApiOperation(value = "Find Ticket", notes = " Api is used to find the Ticket")
	@ApiResponses(value = { @ApiResponse(code = 302, message = "Successfully fetched"),
			@ApiResponse(code = 404, message = "Ticket not found for the given  id") })
	@GetMapping
	public ResponseEntity<ResponseStructure<Ticket>> getTicket(@RequestParam long ticketId) {
		return ticketService.getTicket(ticketId);
	}

	@ApiOperation(value = "Cancel Ticket", notes = " Api is used to cancel the ticket")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully cancelled"),
			@ApiResponse(code = 404, message = "Ticket not found for the given  id") })
	@PutMapping
	public ResponseEntity<ResponseStructure<Ticket>> cancelTicket(@RequestParam long ticketId) {
		return ticketService.cancelTicket(ticketId);
	}
}
