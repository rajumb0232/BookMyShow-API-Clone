package edu.project.bookmyshow.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProductionHouse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productionId;
	private String productionName;
	private LocalDate establishment;

	@ManyToOne
	@JoinColumn
	@JsonIgnore
	private Owner owner;

	@OneToMany(mappedBy = "productionHouse")
	@JsonIgnore
	private List<Movie> movies;
}
