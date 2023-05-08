package edu.project.bookmyshow.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ProductionHouseDto {
	private long productionId;
	@NotBlank(message = "ProductionName cannot be blank")
	@NotNull(message = "ProductionName cannot be null")
	@Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]*", message = "Name should Start with capital letter")
	private String productionName;
	private LocalDate establishment;
}
