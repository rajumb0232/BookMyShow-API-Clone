package edu.project.bookmyshow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.project.bookmyshow.entity.Owner;

public interface OwnerRepo extends JpaRepository<Owner, Long> {

}
