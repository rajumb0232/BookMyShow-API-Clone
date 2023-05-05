package edu.project.bookmyshow.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.bookmyshow.dao.MovieDao;
import edu.project.bookmyshow.dao.ScreenDao;
import edu.project.bookmyshow.dao.ShowDao;
import edu.project.bookmyshow.dto.ShowDto;
import edu.project.bookmyshow.entity.Movie;
import edu.project.bookmyshow.entity.Screen;
import edu.project.bookmyshow.entity.Show;
import edu.project.bookmyshow.enums.ScreenAvailability;
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
			Screen screen = screenDao.getScreen(screenId);
			if (screen != null) {
				show.setScreenId(screenId);
				show.setScreenAvailability(ScreenAvailability.AVAILABLE);
				show.setScreenname(screen.getScreenName());
			} else {
				throw new ScreenNotFoundByIdException("Failed to add Show!!");
			}
			showDao.addShow(show);
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

}
