package edu.project.bookmyshow.service;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.bookmyshow.dao.BookingDao;
import edu.project.bookmyshow.dao.MovieDao;
import edu.project.bookmyshow.dao.ScreenDao;
import edu.project.bookmyshow.dao.ShowDao;
import edu.project.bookmyshow.dao.TicketDao;
import edu.project.bookmyshow.dto.ShowDto;
import edu.project.bookmyshow.entity.Booking;
import edu.project.bookmyshow.entity.Movie;
import edu.project.bookmyshow.entity.Screen;
import edu.project.bookmyshow.entity.Show;
import edu.project.bookmyshow.entity.Ticket;
import edu.project.bookmyshow.enums.BookingStatus;
import edu.project.bookmyshow.enums.ScreenAvailability;
import edu.project.bookmyshow.enums.Screenstatus;
import edu.project.bookmyshow.enums.ShowStatus;
import edu.project.bookmyshow.enums.TicketStatus;
import edu.project.bookmyshow.exception.MovieNotFoundByIdException;
import edu.project.bookmyshow.exception.NullObjectPassedException;
import edu.project.bookmyshow.exception.PastDateTimeSpecifiedException;
import edu.project.bookmyshow.exception.ScreenNotFoundByIdException;
import edu.project.bookmyshow.exception.ShowNotFoundByIdException;
import edu.project.bookmyshow.exception.ShowOnGoingOrClosedException;
import edu.project.bookmyshow.exception.ShowPresentInRequestedTimeException;
import edu.project.bookmyshow.exception.ShowsNotFoundForMovieException;
import edu.project.bookmyshow.exception.ShowsNotFoundInLocationException;
import edu.project.bookmyshow.util.ResponseStructure;

@Service
public class ShowService {

	@Autowired
	private ModelMapper mapper;
	@Autowired
	private MovieDao movieDao;
	@Autowired
	private ScreenDao screenDao;
	@Autowired
	private ShowDao showDao;
	@Autowired
	private TicketDao ticketDao;
	@Autowired
	private BookingDao bookingDao;

	public ResponseEntity<ResponseStructure<Show>> addShow(ShowDto showDto, long movieId, long screenId) {
		if (showDto != null) {
			Show show = (Show) mapper.map(showDto, Show.class);
			/*
			 * checking if the show timing is greater than the previous show run time and
			 * checking if the show run time fits in between the previous and next show
			 */
			List<Show> shows = showDao.getShowsIfPresentBetween(show.getShowStartTime(), show.getShowEndTime());

			if (shows.size() == 0) {
				if (show.getShowStartTime().isBefore(LocalDateTime.now())) {
					throw new PastDateTimeSpecifiedException("Failed to add show!!");
				} else {
					Movie movie = movieDao.getMovie(movieId);
					if (movie != null) {
						show.setMovieId(movieId);
						show.setGenre(movie.getGenre1() + ", " + movie.getGenre2() + ", " + movie.getGenre3());
						show.setLanguage(movie.getLanguage());
						show.setMovieName(movie.getMovieName());
						/**
						 * get the movie duration in string format and split and use to plus the hours,
						 * minutes and seconds to the localDateTime so to avoid user entering the wrong
						 * show end time.
						 */
						show.setMovieDuration(movie.getMovieDuration());
						show.setMovieDescription(movie.getMovieDescription());
					} else {
						throw new MovieNotFoundByIdException("Failed to add Show!!");
					}
					Screen screen = screenDao.getScreenById(screenId);
					if (screen != null) {
						show.setScreenId(screenId);
						show.setScreenName(screen.getScreenName());
						show.setTheatre(screen.getTheatre());
						show.setShowLocation(screen.getTheatre().getAddress().getCity());
						screen.setScreenAvailability(ScreenAvailability.ALLOTTED);
						screen.setScreenstatus(Screenstatus.AVAILABLE);
						/*
						 * whenever a ticket is generated to the show, the screen availability should be
						 * checked & changed.
						 */
					} else {
						throw new ScreenNotFoundByIdException("Failed to add Show!!");
					}
					show.setShowStatus(ShowStatus.ACTIVE);
					/* the show status should be updated using scheduled jobs. */
					showDao.addShow(show);
					screenDao.updateScreen(screenId, screen);
					ResponseStructure<Show> responseStructure = new ResponseStructure<>();
					responseStructure.setStatus(HttpStatus.CREATED.value());
					responseStructure.setMessage("Show added Successfully.");
					responseStructure.setData(show);
					return new ResponseEntity<ResponseStructure<Show>>(responseStructure, HttpStatus.CREATED);
				}

			} else {
				throw new ShowPresentInRequestedTimeException("Failed to add Show!!");
			}

		} else {
			throw new NullObjectPassedException("Failed to add Show!!");
		}
	}

	public ResponseEntity<ResponseStructure<Show>> getShow(long showId) {
		Show show = showDao.getShow(showId);
		if (show != null) {
			ResponseStructure<Show> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("Show Found.");
			responseStructure.setData(show);
			return new ResponseEntity<ResponseStructure<Show>>(responseStructure, HttpStatus.CREATED);
		} else {
			throw new ShowNotFoundByIdException("Failed to find Show!!");
		}
	}

	public ResponseEntity<ResponseStructure<Show>> updadeShow(long showId, ShowDto showDto, long screenId,
			long movieId) {
		Show existing = showDao.getShow(showId);
		if (existing != null) {
			Show show = (Show) mapper.map(showDto, Show.class);
			show.setShowId(showId);
			Screen screen = screenDao.getScreenById(screenId);
			if (screen != null) {
				/*
				 * checking if the show timing is greater than the previous show run time and
				 * checking if the show run time fits in between the previous and next show
				 */
				List<Show> shows = showDao.getShowsIfPresentBetween(show.getShowStartTime(), show.getShowEndTime());
				if (shows.size() == 0) {
					List<Ticket> tickets = ticketDao.getTicketsByShow(show);
					if (tickets.size() == 0) {
						/*
						 * if the show is already having bookings, then the movie and screen cannot be
						 * updated!
						 */
						Movie movie = movieDao.getMovie(movieId);
						if (movie != null) {
							show.setMovieId(movieId);
							show.setGenre(movie.getGenre1() + ", " + movie.getGenre2() + ", " + movie.getGenre3());
							show.setLanguage(movie.getLanguage());
							show.setMovieName(movie.getMovieName());
							show.setMovieDuration(movie.getMovieDuration());
							show.setMovieDescription(movie.getMovieDescription());
						} else {
							throw new MovieNotFoundByIdException("Failed to update Show!!");
						}
						show.setScreenId(screenId);
						show.setScreenName(screen.getScreenName());
						show.setTheatre(screen.getTheatre());
						show.setShowLocation(screen.getTheatre().getAddress().getCity());
					}
				} else {
					throw new ScreenNotFoundByIdException("Failed to update Show!!");
				}

				show.setShowStatus(existing.getShowStatus());
				showDao.addShow(show);
				ResponseStructure<Show> responseStructure = new ResponseStructure<>();
				responseStructure.setStatus(HttpStatus.OK.value());
				responseStructure.setMessage("Show updated Successfully.");
				responseStructure.setData(show);
				return new ResponseEntity<ResponseStructure<Show>>(responseStructure, HttpStatus.OK);
			} else {
				throw new ShowPresentInRequestedTimeException("Failed to update Show!!");
			}
		} else {
			throw new NullObjectPassedException("Failed to update Show!!");
		}
	}

	/**
	 * check if the showStatus is already being cancelled, check the booking status,
	 * do not update to expired if already cancelled
	 */
	public ResponseEntity<ResponseStructure<Show>> cancelShow(long showId) {
		Show show = showDao.getShow(showId);
		if (show != null) {
			if (show.getShowStatus().equals(ShowStatus.ACTIVE)) {
				Screen screen = screenDao.getScreenById(show.getScreenId());
				screen.setScreenstatus(Screenstatus.AVAILABLE);
				screen.setScreenAvailability(ScreenAvailability.NOT_ALLOTTED);

				List<Ticket> tickets = ticketDao.getTicketsByShow(show);
				if (tickets != null) {
					for (Ticket ticket : tickets) {
						List<Booking> bookings = ticket.getBookings();
						for (Booking booking : bookings) {
							booking.setBookingStatus(BookingStatus.CANCELLED);
							bookingDao.saveBooking(booking);
						}
						ticket.setTicketStatus(TicketStatus.CANCELLED);
						ticketDao.cancelTicket(ticket);
					}
					show.setShowStatus(ShowStatus.CANCELLED);
					screenDao.cancelShow(screen);
					showDao.cancelShow(show);
				}
				ResponseStructure<Show> responseStructure = new ResponseStructure<>();
				responseStructure.setStatus(HttpStatus.OK.value());
				responseStructure.setMessage("Show Cancelled Successfully.");
				responseStructure.setData(show);
				return new ResponseEntity<ResponseStructure<Show>>(responseStructure, HttpStatus.OK);
			} else
				throw new ShowOnGoingOrClosedException("Failed to Cancel Show!!");

		} else {
			throw new ShowNotFoundByIdException("Failed to Cancel Show!!");
		}

	}

	public ResponseEntity<ResponseStructure<List<Show>>> getShowsByCity(String city, ShowStatus showStatus) {
		List<Show> shows = showDao.getShowsByCity(city, showStatus);
		if (shows != null) {
			if (shows.isEmpty()) {
				throw new ShowsNotFoundInLocationException("Failed to find Shows!!");
			} else {
				ResponseStructure<List<Show>> responseStructure = new ResponseStructure<>();
				responseStructure.setStatus(HttpStatus.FOUND.value());
				responseStructure.setMessage("Shows Found.");
				responseStructure.setData(shows);
				return new ResponseEntity<ResponseStructure<List<Show>>>(responseStructure, HttpStatus.FOUND);
			}
		} else {
			throw new ShowsNotFoundInLocationException("Failed to find Shows!!");
		}
	}

	public ResponseEntity<ResponseStructure<List<Show>>> getShowsByMovieId(long movieId, ShowStatus showStatus) {
		Movie movie = movieDao.getMovie(movieId);
		if (movie != null) {
			List<Show> shows = showDao.getShowsByMovieId(movieId, showStatus);
			if (shows != null) {
				if (shows.isEmpty()) {
					throw new ShowsNotFoundInLocationException("Failed to find Shows!!");
				} else {
					ResponseStructure<List<Show>> responseStructure = new ResponseStructure<>();
					responseStructure.setStatus(HttpStatus.FOUND.value());
					responseStructure.setMessage("Shows Found.");
					responseStructure.setData(shows);
					return new ResponseEntity<ResponseStructure<List<Show>>>(responseStructure, HttpStatus.FOUND);
				}
			} else {
				throw new ShowsNotFoundForMovieException("Failed to find Shows!!");
			}
		} else
			throw new MovieNotFoundByIdException("Failed to find shows!!");
	}

}
