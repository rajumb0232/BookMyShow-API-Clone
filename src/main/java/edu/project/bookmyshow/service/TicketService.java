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
import edu.project.bookmyshow.enums.BookingStatus;
import edu.project.bookmyshow.enums.SeatType;
import edu.project.bookmyshow.enums.ShowStatus;
import edu.project.bookmyshow.enums.TicketStatus;
import edu.project.bookmyshow.exception.CustomerNotFoundByIdException;
import edu.project.bookmyshow.exception.SeatAlreadyBookedException;
import edu.project.bookmyshow.exception.SeatNotFoundByIdException;
import edu.project.bookmyshow.exception.ShowNotFoundByIdException;
import edu.project.bookmyshow.exception.ShowOnGoingOrClosedException;
import edu.project.bookmyshow.exception.TicketAlreadyCancelledException;
import edu.project.bookmyshow.exception.TicketAlreadyExpiredException;
import edu.project.bookmyshow.exception.TicketCannotBeCancelledException;
import edu.project.bookmyshow.exception.TicketNotFoundByIdException;
import edu.project.bookmyshow.util.ResponseStructure;

@Service
public class TicketService {

	@Autowired
	private TicketDao ticketDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private ShowDao showDao;
	@Autowired
	private SeatDao seatDao;
	@Autowired
	private BookingDao bookingDao;

	public ResponseEntity<ResponseStructure<Ticket>> bookTicket(long customerId, long showId, Long[] seatId) {
		Ticket ticket = new Ticket();
		Customer customer = customerDao.getCustomerById(customerId);
		if (customer != null) {
			ticket.setCustomer(customer);
		} else {
			throw new CustomerNotFoundByIdException("failed to book ticket!!");
		}
		Show show = showDao.getShow(showId);
		if (show != null) {
			if (show.getShowStatus().equals(ShowStatus.ACTIVE)) {
				ticket.setShow(show);
			} else {
				throw new ShowOnGoingOrClosedException("Failed to book ticket!!");
			}

		} else {
			throw new ShowNotFoundByIdException("Failed to book ticket!!");
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
				Booking booking = bookingDao.getBookingByTime(show.getShowStartTime(), show.getShowEndTime(), id);
				if (booking == null) {
					booking = new Booking();
					booking.setSeatId(seat.getSeatId());
					booking.setSeatType(seat.getSeatType());
					booking.setBookingStatus(BookingStatus.ACTIVE);
					booking.setBookedFromTime(show.getShowStartTime());
					booking.setBookedTillTime(show.getShowEndTime());
					
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

				} else
					throw new SeatAlreadyBookedException("Failed to Book Ticket!!");
			} else
				throw new SeatNotFoundByIdException("Failed to Book Ticket!!");

		}
		for (Booking booking : bookings) {
			bookingDao.saveBooking(booking);
		}
		ticket.setTicketStatus(TicketStatus.ACTIVE);
		ticket.setTotalPrice(totalPrice);
		ticket.setBookings(bookings);
		ticket = ticketDao.bookTicket(ticket);
		ResponseStructure<Ticket> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Ticket Booked Successfully.");
		responseStructure.setData(ticket);
		return new ResponseEntity<ResponseStructure<Ticket>>(responseStructure, HttpStatus.CREATED);
	}

	
	public ResponseEntity<ResponseStructure<Ticket>> cancelTicket(long ticketId) {
		Ticket ticket = ticketDao.getTicket(ticketId);
		if (ticket != null) {
			if (ticket.getShow().getShowStatus().equals(ShowStatus.ON_GOING)) {
				throw new TicketCannotBeCancelledException("Failed to Cancel Ticket!!");
			} else {
				if (ticket.getTicketStatus().equals(TicketStatus.EXPIRED)) {
					throw new TicketAlreadyExpiredException("Failed to Cancel Ticekt!!");
				} else {
					if (ticket.getTicketStatus().equals(TicketStatus.CANCELLED)) {
						throw new TicketAlreadyCancelledException("Failed to cancel Ticket!!");
					} else {
						List<Booking> booking = ticket.getBookings();
						for (Booking b : booking) {
							b.setBookingStatus(BookingStatus.CANCELLED);
							bookingDao.saveBooking(b);
						}
						ticket.setTicketStatus(TicketStatus.CANCELLED);

						ResponseStructure<Ticket> responseStructure = new ResponseStructure<>();
						responseStructure.setStatus(HttpStatus.OK.value());
						responseStructure.setMessage("Ticket Cancelled Successfully.");
						responseStructure.setData(ticket);
						return new ResponseEntity<ResponseStructure<Ticket>>(responseStructure, HttpStatus.OK);
					}
				}
			}

		} else {
			throw new TicketNotFoundByIdException("Failed to Cancel Ticket!!");
		}
	}

	
	public ResponseEntity<ResponseStructure<Ticket>> getTicket(long ticketId) {
		Ticket ticket = ticketDao.getTicket(ticketId);
		if (ticket != null) {
			ResponseStructure<Ticket> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Ticket Found.");
			responseStructure.setData(ticket);
			return new ResponseEntity<ResponseStructure<Ticket>>(responseStructure, HttpStatus.FOUND);
		} else {
			throw new TicketNotFoundByIdException("Failed to Find Ticket!!");
		}
	}

}
