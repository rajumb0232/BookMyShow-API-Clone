package edu.project.bookmyshow.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.project.bookmyshow.entity.Show;

public interface ShowRepo extends JpaRepository<Show, Long> {
	
	@Query(value = "select s from Show s where s.showStartTime between ?1 and ?2")
	public Optional<List<Show>> getShowsIfPresentBetween(LocalDateTime startTime, LocalDateTime endTime);
}
