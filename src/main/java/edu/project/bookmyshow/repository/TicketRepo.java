package edu.project.bookmyshow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.project.bookmyshow.entity.Ticket;

public interface TicketRepo extends JpaRepository<Ticket, Long>{

}
