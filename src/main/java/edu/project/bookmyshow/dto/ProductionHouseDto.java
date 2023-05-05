package edu.project.bookmyshow.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ProductionHouseDto {
	private long productionId;
	private String productionName;
	private LocalDate establishment;
}
