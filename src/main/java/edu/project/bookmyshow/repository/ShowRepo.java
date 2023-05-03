package edu.project.bookmyshow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.project.bookmyshow.entity.Show;

public interface ShowRepo extends JpaRepository<Show, Long> {

}
