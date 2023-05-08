package edu.project.bookmyshow.dto;

import edu.project.bookmyshow.enums.TicketStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDto {
	private long ticketId;
	private double totalPrice;
	private TicketStatus ticketStatus;
}
