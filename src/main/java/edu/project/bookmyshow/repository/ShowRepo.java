package edu.project.bookmyshow.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.project.bookmyshow.entity.Show;
import edu.project.bookmyshow.enums.ShowStatus;

public interface ShowRepo extends JpaRepository<Show, Long> {
	
	/*
	 * method is used to check if the shows present in between the requested
	 * show start and time, */
	@Query(value = "select s from Show s where s.showStartTime between ?1 and ?2")
	public Optional<List<Show>> getShowsIfPresentBetween(LocalDateTime startTime, LocalDateTime endTime);
	
	
	/*
	 * method is used fetch the list of shows with the given location
	 * at the status active*/
	@Query(value = "select s from Show s where s.showLocation=?1 and s.showStatus=?2")
	public Optional<List<Show>> getShowsByCity(String city, ShowStatus showStatus);
	

	/*
	 * method is used to fetch the available shows for a particular movie
	 * at the status active*/
	@Query(value = "select s from Show s where s.movieId=?1 and s.showStatus=?2")
	public Optional<List<Show>> getShowsByMovieId(long movieId, ShowStatus showStatus);
	
	
	/*
	 * method is used to fetch all the shows that is having show start time within
	 *  or less than the current localDateTime */
	@Query(value = "select s from Show s where s.showStartTime<=?1 and s.showStatus=?2")
	public Optional<List<Show>> getShowsByTime(LocalDateTime dateTime, ShowStatus active);
	
	
	/*
	 * method is used to fetch all the shows by show end time with status on_going within
	 *  or less than the current localDateTime */
	@Query(value = "select s from Show s where s.showEndTime<=?1 and s.showStatus=?2")
	public Optional<List<Show>> getClosedShows(LocalDateTime dateTime, ShowStatus showStatus);
}
