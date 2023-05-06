package edu.project.bookmyshow.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.project.bookmyshow.entity.Show;
import edu.project.bookmyshow.entity.Ticket;

public interface TicketRepo extends JpaRepository<Ticket, Long>{
	
	@Query(value = "select t from Ticket t where t.show=?1")
	public Optional<List<Ticket>> getTicketsByShow(Show show);
}
