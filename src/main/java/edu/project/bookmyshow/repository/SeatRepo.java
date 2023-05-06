package edu.project.bookmyshow.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.project.bookmyshow.entity.Screen;
import edu.project.bookmyshow.entity.Seat;
import edu.project.bookmyshow.enums.SeatStatus;

public interface SeatRepo extends JpaRepository<Seat, Long>{
	
	@Query(value = "select s from Seat s where s.seatStatus=?1 and s.screen=?2")
	public Optional<List<Seat>> getSeatsByStatusByScreen(SeatStatus seatStatus, Screen screen);

}
