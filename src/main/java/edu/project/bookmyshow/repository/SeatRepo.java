package edu.project.bookmyshow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.project.bookmyshow.entity.Seat;

public interface SeatRepo extends JpaRepository<Seat, Long>{
	
//	@Query(value = "select s from Seat s where s.seatStatus=?1 and s.screen=?2")
//	public Optional<List<Seat>> getSeatsByStatusByScreen(SeatStatus seatStatus, Screen screen);

}
