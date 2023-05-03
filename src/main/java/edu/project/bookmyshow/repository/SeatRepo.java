package edu.project.bookmyshow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.project.bookmyshow.entity.Seat;

public interface SeatRepo extends JpaRepository<Seat, Long>{

}
