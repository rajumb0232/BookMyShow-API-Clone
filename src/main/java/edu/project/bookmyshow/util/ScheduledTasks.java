package edu.project.bookmyshow.util;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import edu.project.bookmyshow.dao.ScreenDao;
import edu.project.bookmyshow.dao.SeatDao;
import edu.project.bookmyshow.dao.ShowDao;
import edu.project.bookmyshow.dao.TicketDao;
import edu.project.bookmyshow.entity.Screen;
import edu.project.bookmyshow.entity.Seat;
import edu.project.bookmyshow.entity.Show;
import edu.project.bookmyshow.entity.Ticket;
import edu.project.bookmyshow.enums.ScreenAvailability;
import edu.project.bookmyshow.enums.Screenstatus;
import edu.project.bookmyshow.enums.SeatStatus;
import edu.project.bookmyshow.enums.ShowStatus;
import edu.project.bookmyshow.enums.TicketStatus;

@Component
public class ScheduledTasks {
	
	@Autowired
	private ShowDao showDao;
	@Autowired
	private ScreenDao screenDao;
	@Autowired
	private SeatDao seatDao;
	@Autowired
	private TicketDao ticketDao;
	
	/**
	 * method to reset
	 * show status to Closed
	 * the screen availability to not_alloted, screenStatus to available
	 * seat status to available
	 * ticket status to expired 
	 * -- once the show gets completed
	 *  ---------- get shows by showEndTime
	 *  
	 *  *****************
	 *  
	 *  method to reset 
	 *  the show status to ongoing */
	
	@Scheduled(cron = "0 * * * * *")
	public void setShowToOnGoing() {
		/*
		 * method is used to fetch all the shows that is having show start time within
		 *  or less than the current localDateTime */
		List<Show> shows = showDao.getShowsByTime(LocalDateTime.now());
		if(shows!=null) {
			if(shows.size()>0) {
				for(Show show : shows) {
					show.setShowStatus(ShowStatus.ON_GOING);
					showDao.updateShow(show);
				}
			}
		}
	}
	
	@Scheduled(cron = "0 * * * * *")
	public void setShowToClosed() {
		List<Show> shows = showDao.getClosedShows(LocalDateTime.now(), ShowStatus.ON_GOING);
		if(shows!=null) {
			if(shows.size()>0) {
				System.err.println("hii");
				for(Show show : shows) {
					Screen screen = screenDao.getScreenById(show.getScreenId());
					screen.setScreenAvailability(ScreenAvailability.NOT_ALLOTTED);
					screen.setScreenstatus(Screenstatus.AVAILABLE);
					List<Seat> seats = seatDao.getSeatsByStatusByScreen(SeatStatus.BLOCKED, screen);
					for(Seat seat : seats) {
						seat.setSeatStatus(SeatStatus.AVAILABLE);
						seatDao.updateSeat(seat);
					}
					seats = seatDao.getSeatsByStatusByScreen(SeatStatus.BOOKED, screen);
					for(Seat seat : seats) {
						seat.setSeatStatus(SeatStatus.AVAILABLE);
						seatDao.updateSeat(seat);
					}
					screenDao.saveScreen(screen);
					List<Ticket> tickets = ticketDao.getTicketsByShow(show);
					for(Ticket ticket : tickets) {
						ticket.setTicketStatus(TicketStatus.EXPIRED);
						ticketDao.bookTicket(ticket);
					}
					show.setShowStatus(ShowStatus.CLOSED);
					showDao.updateShow(show);
				}
			}
		}
	}
}
