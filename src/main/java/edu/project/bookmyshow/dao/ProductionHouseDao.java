package edu.project.bookmyshow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.project.bookmyshow.entity.ProductionHouse;

import edu.project.bookmyshow.repository.ProductionHouseRepo;

@Repository
public class ProductionHouseDao {
	@Autowired
	private ProductionHouseRepo  repo;
	public ProductionHouse addProductionHouse(ProductionHouse house) {
		return repo.save(house);
	}
	public ProductionHouse updateProductionHouse(long houseId,ProductionHouse house) {
		Optional<ProductionHouse> optional=repo.findById(houseId);
		if(optional.isPresent()) {
			house.setProductionId(houseId);
			return repo.save(house);
		}
		return null;
	}
	public ProductionHouse deleteProductionHouse(long houseId) {
		Optional<ProductionHouse> optional=repo.findById(houseId);
		if(optional.isPresent()) {
			ProductionHouse house=optional.get();
			 repo.delete(house);
			 return optional.get();
		}
		return null;
	}
	public ProductionHouse findProductionHouse(long houseId) {
		Optional<ProductionHouse> optional=repo.findById(houseId);
		if(optional.isPresent()) {
		
			 return optional.get();
		}
		return null;
	
	}
}
