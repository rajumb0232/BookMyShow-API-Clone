package edu.project.bookmyshow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.project.bookmyshow.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Long>{

}
