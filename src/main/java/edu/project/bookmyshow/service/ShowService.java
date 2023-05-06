package edu.project.bookmyshow.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.bookmyshow.dao.MovieDao;
import edu.project.bookmyshow.dao.ScreenDao;
import edu.project.bookmyshow.dao.SeatDao;
import edu.project.bookmyshow.dao.ShowDao;
import edu.project.bookmyshow.dto.ShowDto;
import edu.project.bookmyshow.entity.Movie;
import edu.project.bookmyshow.entity.Screen;
import edu.project.bookmyshow.entity.Seat;
import edu.project.bookmyshow.entity.Show;
import edu.project.bookmyshow.enums.ScreenAvailability;
import edu.project.bookmyshow.enums.Screenstatus;
import edu.project.bookmyshow.enums.SeatStatus;
import edu.project.bookmyshow.enums.ShowStatus;
import edu.project.bookmyshow.exception.MovieNotFoundByIdException;
import edu.project.bookmyshow.exception.NullObjectPassedException;
import edu.project.bookmyshow.exception.ScreenNotFoundByIdException;
import edu.project.bookmyshow.exception.ShowNotFoundByIdException;
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
	private SeatDao seatDao;

	public ResponseEntity<ResponseStructure<Show>> addShow(ShowDto showDto, long movieId, long screenId) {
		if (showDto != null) {
			Show show = (Show) mapper.map(showDto, Show.class);
			Movie movie = movieDao.getMovie(movieId);
			if (movie != null) {
				show.setMovieId(movieId);
				show.setGenre(movie.getGenre1());
				show.setLanguage(movie.getLanguage());
				show.setMovieNaame(movie.getMovieName());
				show.setMovieDuration(movie.getMovieDuration());
				show.setMovieDescription(movie.getMovieDescription());
			} else {
				throw new MovieNotFoundByIdException("Failed to add Show!!");
			}
			Screen screen = screenDao.getScreenById(screenId);
			if (screen != null) {
				show.setScreenId(screenId);
				show.setScreenname(screen.getScreenName());
				show.setTheatre(screen.getTheatre());
				screen.setScreenAvailability(ScreenAvailability.ALLOTTED);
				screen.setScreenstatus(Screenstatus.AVAILABLE);
				/*whenever a ticket is generated to the show, the screen availability 
				 * should be checked & changed.*/
			} else {
				throw new ScreenNotFoundByIdException("Failed to add Show!!");
			}
			show.setShowStatus(ShowStatus.ACTIVE);
			/*the show status should be updated using scheduled jobs.*/
			showDao.addShow(show);
			screenDao.updateScreen(screenId, screen);
			ResponseStructure<Show> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("Show added Successfully.");
			responseStructure.setData(show);
			return new ResponseEntity<ResponseStructure<Show>>(responseStructure, HttpStatus.CREATED);
		} else {
			throw new NullObjectPassedException("Failed to add Show!!");
		}
	}

	
	
	public ResponseEntity<ResponseStructure<Show>> getShow(long showId) {
		Show show = showDao.getShow(showId);
		if (show != null) {
			ResponseStructure<Show> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("Show added Successfully.");
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
				List<Seat> seats = seatDao.getSeatsByStatusByScreen(SeatStatus.BOOKED, screen);
					if(seats.size()==0) {
						/*if the show is already having bookings, the the movie
						 * and screen cannot be updated!*/
							Movie movie = movieDao.getMovie(movieId);
							if (movie != null) {
								show.setMovieId(movieId);
								show.setGenre(movie.getGenre1());
								show.setLanguage(movie.getLanguage());
								show.setMovieNaame(movie.getMovieName());
								show.setMovieDuration(movie.getMovieDuration());
								show.setMovieDescription(movie.getMovieDescription());
							} else {
								throw new MovieNotFoundByIdException("Failed to update Show!!");
							}
							show.setScreenId(screenId);
							show.setScreenname(screen.getScreenName());
							show.setTheatre(screen.getTheatre());
					}
			} else {
				throw new ScreenNotFoundByIdException("Failed to update Show!!");
			}
	
			show.setShowStatus(existing.getShowStatus());
			showDao.addShow(show);
			ResponseStructure<Show> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("Show updated Successfully.");
			responseStructure.setData(show);
			return new ResponseEntity<ResponseStructure<Show>>(responseStructure, HttpStatus.CREATED);
		} else {
			throw new NullObjectPassedException("Failed to update Show!!");
		}
	}

	/**
	 * write a method to cancel show.
	 * note: should have to make sure if the showStatus is set to update as cancelled,
	 * set back the screenAvailability as not_alloted &
	 * set back the screenStatus as Available,
	 * set all the  related tickets status to cancelled,
	 * and set back all the seat status to Available*/
}
