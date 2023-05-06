package edu.project.bookmyshow.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.bookmyshow.dao.BookingDao;
import edu.project.bookmyshow.dao.CustomerDao;
import edu.project.bookmyshow.dao.SeatDao;
import edu.project.bookmyshow.dao.ShowDao;
import edu.project.bookmyshow.dao.TicketDao;
import edu.project.bookmyshow.entity.Booking;
import edu.project.bookmyshow.entity.Customer;
import edu.project.bookmyshow.entity.Seat;
import edu.project.bookmyshow.entity.Show;
import edu.project.bookmyshow.entity.Ticket;
import edu.project.bookmyshow.enums.SeatStatus;
import edu.project.bookmyshow.enums.SeatType;
import edu.project.bookmyshow.enums.TicketStatus;
import edu.project.bookmyshow.exception.CustomerNotFoundByIdException;
import edu.project.bookmyshow.exception.SeatAlreadyBookedException;
import edu.project.bookmyshow.exception.SeatTemporarilyBlockedException;
import edu.project.bookmyshow.exception.ShowNotFoundByIdException;
import edu.project.bookmyshow.util.ResponseStructure;

@Service
public class TicketService {

	@Autowired
	private TicketDao ticketDao;
//	@Autowired
//	private ModelMapper mapper;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private ShowDao showDao;
	@Autowired
	private SeatDao seatDao;
	@Autowired
	private BookingDao bookingDao;

	public ResponseEntity<ResponseStructure<Ticket>> bookTicket(long customerId, long showId,
			Long[] seatId) {
		Ticket ticket = new Ticket();
		Customer customer = customerDao.getCustomerById(customerId);
		if (customer != null) {
			ticket.setCustomer(customer);
		} else {
			throw new CustomerNotFoundByIdException("failed to add ticket!!");
		}
		Show show = showDao.getShow(showId);
		if (show != null) {
			ticket.setShow(show);
		} else {
			throw new ShowNotFoundByIdException("Failed to add ticket!!");
		}
		List<Booking> bookings = new ArrayList<>();
		List<Seat> seats = new ArrayList<>();
		double totalPrice = 0;

		/*
		 * iterating over for each seats and creating a booking for requested seats, and
		 * setting the price
		 */
		for (Long id : seatId) {
			Seat seat = seatDao.getSeat(id);
			if (seat != null) {
				if (seat.getSeatStatus().equals(SeatStatus.BOOKED)) {
					throw new SeatAlreadyBookedException("Failed to book ticket");
				} else {
					Booking booking = new Booking();
					booking.setSeatId(seat.getSeatId());
					if (seat.getSeatStatus().equals(SeatStatus.BLOCKED)) {
						throw new SeatTemporarilyBlockedException("Failed to book ticket!!");
					} else {
						booking.setSeatType(seat.getSeatType());
						SeatType seatType = booking.getSeatType();

						switch (seatType) {
						case CLASSIC:
							booking.setSeatPrice(show.getClassicSeatPrice());
							totalPrice += show.getClassicSeatPrice();
							break;

						case GOLD:
							booking.setSeatPrice(show.getGoldSeatPrice());
							totalPrice += show.getGoldSeatPrice();
							break;

						case PREMIUM:
							booking.setSeatPrice(show.getPremiumSeatPrice());
							totalPrice += show.getPremiumSeatPrice();
							break;
						}

						bookings.add(booking);
						seats.add(seat);
					}
					bookingDao.saveBooking(booking);
				}

			}
			// save booking through bookingDao.
			
		}
		ticket.setTicketStatus(TicketStatus.ACTIVE);
		ticket.setTotalPrice(totalPrice);
		ticket.setBookings(bookings);
		ticket = ticketDao.bookTicket(ticket);
		if (ticket != null) {
			for (Seat seat : seats) {
				seat.setSeatStatus(SeatStatus.BOOKED);
				seatDao.updateSeat(seat);
			}
		}
		ResponseStructure<Ticket> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Ticket Booked Successfully.");
		responseStructure.setData(ticket);
		return new ResponseEntity<ResponseStructure<Ticket>>(responseStructure, HttpStatus.CREATED);

	}

	/**
	 * create a method for scheduled job, where this method will responsible to set
	 * the seatStatus to available after exceeding the show time all the tickets
	 * related to the show should get expired. if the show gets cancelled, the
	 * ticket status should also be cancelled if they are active.
	 */

}
