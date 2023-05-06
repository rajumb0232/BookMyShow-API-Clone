package edu.project.bookmyshow.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import edu.project.bookmyshow.dao.MovieDao;
import edu.project.bookmyshow.dao.ProductionHouseDao;

import edu.project.bookmyshow.dto.MovieDto;

import edu.project.bookmyshow.entity.Movie;
import edu.project.bookmyshow.entity.ProductionHouse;

import edu.project.bookmyshow.exception.MovieNotFoundByIdException;
import edu.project.bookmyshow.exception.ProductionNotFoundByIdException;
import edu.project.bookmyshow.util.ResponseStructure;

@Service
public class MovieService {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private MovieDao dao;
	@Autowired
	private ProductionHouseDao houseDao;
	
	public ResponseEntity<ResponseStructure<Movie>> saveMovie(@RequestParam long houseId,@RequestBody MovieDto movieDto){
		ProductionHouse house=houseDao.findProductionHouse(houseId);
		if(house!=null) {
			Movie movie = (Movie) this.modelMapper.map(movieDto, Movie.class);
			movie.setProductionHouse(house);
		   Movie movie2=dao.addMovie(movie);
		   ResponseStructure<Movie> structure=new ResponseStructure<Movie>();
		   structure.setData(movie2);
		   structure.setMessage("Movie Saved Successfully");
		   structure.setStatus(HttpStatus.CREATED.value());
		   List<Movie> list=new ArrayList<Movie>();
		   list.add(movie2);
		   houseDao.updateProductionHouse(houseId, house);
			return new ResponseEntity<ResponseStructure<Movie>>(structure, HttpStatus.CREATED);
		}else {
			throw new ProductionNotFoundByIdException("Failed to add movie !!");
		}
	}

	public ResponseEntity<ResponseStructure<Movie>> getMovieById(long movieId) {
		Movie movie=dao.getMovie(movieId);
		if(movie!=null) {
			 ResponseStructure<Movie> structure=new ResponseStructure<Movie>();
			   structure.setData(movie);
			   structure.setMessage("Movie Fetched Successfully");
			   structure.setStatus(HttpStatus.FOUND.value());
				return new ResponseEntity<ResponseStructure<Movie>>(structure, HttpStatus.FOUND);
		}else {
			throw new MovieNotFoundByIdException("Failed to fetch movie !!");
		}
	}
	public ResponseEntity<ResponseStructure<Movie>> deleteMovieById(long movieId) {
		Movie movie=dao.deleteMovie(movieId);
		if(movie!=null) {
			 ResponseStructure<Movie> structure=new ResponseStructure<Movie>();
			   structure.setData(movie);
			   structure.setMessage("Movie deleted Successfully");
			   structure.setStatus(HttpStatus.FOUND.value());
				return new ResponseEntity<ResponseStructure<Movie>>(structure, HttpStatus.FOUND);
		}else {
			throw new MovieNotFoundByIdException("Failed to delete movie !!");
		}
	}
	
	public ResponseEntity<ResponseStructure<Movie>> updateMovie(long movieId,MovieDto dto){
		Movie movie = this.modelMapper.map(dto, Movie.class);
		Movie movie2=dao.getMovie(movieId);
		if(movie2!=null) {
			movie.setProductionHouse(movie2.getProductionHouse());
		}else {
			throw new MovieNotFoundByIdException("Failed to update movie !!");
		}
		
		Movie movie1=dao.updateMovie(movieId,movie);
		if(movie1!=null) {
			ResponseStructure<Movie> structure=new ResponseStructure<Movie>();
			   structure.setData(movie1);
			   structure.setMessage("Movie Updated Successfully");
			   structure.setStatus(HttpStatus.FOUND.value());
				return new ResponseEntity<ResponseStructure<Movie>>(structure, HttpStatus.FOUND);
		}else {
			throw new MovieNotFoundByIdException("Failed to update movie !!");
		}
	}
	

}
