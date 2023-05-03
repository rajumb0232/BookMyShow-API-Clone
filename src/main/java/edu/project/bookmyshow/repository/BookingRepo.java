package edu.project.bookmyshow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.project.bookmyshow.entity.Booking;

public interface BookingRepo  extends JpaRepository<Booking, Long>{

}
