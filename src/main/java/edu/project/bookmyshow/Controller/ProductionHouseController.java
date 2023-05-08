package edu.project.bookmyshow.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.project.bookmyshow.dto.ProductionHouseDto;
import edu.project.bookmyshow.entity.ProductionHouse;
import edu.project.bookmyshow.service.ProductionHouseService;
import edu.project.bookmyshow.util.ResponseStructure;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/productionhouse")
public class ProductionHouseController {
	@Autowired
	private ProductionHouseService houseService;

	@ApiOperation(value = "Save ProductionHouse", notes = " Api is used to save the ProductionHouse")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created"),
			@ApiResponse(code = 404, message = "ProductionHouse not found for the given  id") })
	@PostMapping
	public ResponseEntity<ResponseStructure<ProductionHouse>> saveProductionHouse(@RequestParam long ownerId,
			@Valid @RequestBody ProductionHouseDto houseDto) {
		return houseService.saveProductionHouse(ownerId, houseDto);
	}

	@ApiOperation(value = "Update ProductionHouse", notes = " Api is used to update the ProductionHouse")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully Updated"),
			@ApiResponse(code = 404, message = "ProductionHouse not found for the given  id") })
	@PutMapping
	public ResponseEntity<ResponseStructure<ProductionHouse>> updateProductionHouse(@RequestParam long houseId,
			@Valid @RequestBody ProductionHouseDto dto) {
		return houseService.updateProductionHouse(houseId, dto);
	}

	@ApiOperation(value = "Delete ProductionHouse", notes = " Api is used to delete the ProductionHouse")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted"),
			@ApiResponse(code = 404, message = "ProductionHouse not found for the given  id") })
	@DeleteMapping
	public ResponseEntity<ResponseStructure<ProductionHouse>> deleteProductionHouse(@RequestParam long houseId) {
		return houseService.deleteProductionHouse(houseId);
	}

	@ApiOperation(value = "Find ProductionHouse", notes = " Api is used to find the ProductionHouse")
	@ApiResponses(value = { @ApiResponse(code = 302, message = "Successfully fetched"),
			@ApiResponse(code = 404, message = "ProductionHouse not found for the given  id") })
	@GetMapping
	public ResponseEntity<ResponseStructure<ProductionHouse>> findProductionHouse(@RequestParam long houseId) {
		return houseService.getProductionHouse(houseId);
	}

}
