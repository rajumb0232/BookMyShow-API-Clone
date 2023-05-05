package edu.project.bookmyshow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.project.bookmyshow.entity.Address;
import edu.project.bookmyshow.entity.Theatre;

public interface AddressRepo extends JpaRepository<Address, Long> {
@Query("select a.theatre from Address a where a.city=?1")
	public List<Theatre> getTheatresBYCity(String city);

}
