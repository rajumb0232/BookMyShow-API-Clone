package edu.project.bookmyshow.Controller;

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

@RestController
@RequestMapping("/productionhouse")
public class ProductionHouseController {
	@Autowired
	private ProductionHouseService houseService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<ProductionHouse>> saveProductionHouse(@RequestParam long ownerId,@RequestBody ProductionHouseDto houseDto){
		return houseService.saveProductionHouse(ownerId, houseDto);
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<ProductionHouse>> updateProductionHouse(@RequestParam long houseId,@RequestBody ProductionHouseDto dto){
		return houseService.updateProductionHouse(houseId, dto);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<ProductionHouse>> deleteProductionHouse(@RequestParam long houseId){
		return houseService.deleteProductionHouse(houseId);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<ProductionHouse>> findProductionHouse(@RequestParam long houseId){
		return houseService.getProductionHouse(houseId);
	}

}
