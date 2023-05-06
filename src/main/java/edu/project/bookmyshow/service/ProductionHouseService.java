package edu.project.bookmyshow.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.bookmyshow.dao.OwnerDao;
import edu.project.bookmyshow.dao.ProductionHouseDao;
import edu.project.bookmyshow.dto.ProductionHouseDto;
import edu.project.bookmyshow.entity.Owner;
import edu.project.bookmyshow.entity.ProductionHouse;
import edu.project.bookmyshow.exception.OwnerNotFoundByIdException;
import edu.project.bookmyshow.exception.ProductionNotFoundByIdException;
import edu.project.bookmyshow.util.ResponseStructure;

@Service
public class ProductionHouseService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ProductionHouseDao houseDao;
	@Autowired
	private OwnerDao ownerDao;
	public ResponseEntity<ResponseStructure<ProductionHouse>> saveProductionHouse(long ownerId,ProductionHouseDto houseDto){
		Owner owner=ownerDao.getOwnerById(ownerId);

		ProductionHouse house =new ProductionHouse();
		house.setEstablishment(houseDto.getEstablishment());
		house.setProductionName(houseDto.getProductionName());
		if(owner!=null) {
		   	ResponseStructure<ProductionHouse> structure = new ResponseStructure<>();
            house.setOwner(owner);
            ProductionHouse house1 =houseDao.addProductionHouse(house);
            if(house1!=null) {
         
               structure.setMessage("ProductionHouse Saved Successfully");
               structure.setStatus(HttpStatus.CREATED.value());
               structure.setData(house1);
               
            }
            return new ResponseEntity<ResponseStructure<ProductionHouse>>(structure, HttpStatus.CREATED); 
			
		}else {
			throw new OwnerNotFoundByIdException("Failed to add ProductionHouse!!");
//			ownerIdNotFOund
		}
		
	}
	public ResponseEntity<ResponseStructure<ProductionHouse>> updateProductionHouse(long houseId,ProductionHouseDto dto){

		ProductionHouse house =(ProductionHouse)this.modelMapper.map(dto, ProductionHouse.class);
		ProductionHouse house1=houseDao.updateProductionHouse(houseId, house);
		if(house1!=null) {
			ResponseStructure<ProductionHouse> structure = new ResponseStructure<ProductionHouse>();
			structure.setMessage("ProductionHouse updated successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(house1);
			return new ResponseEntity<ResponseStructure<ProductionHouse>>(structure, HttpStatus.OK);
		}else {
//			throw ProductionHouseId found exception
			throw new ProductionNotFoundByIdException("Failed to update ProductionHouse!!");
		}
	}
	public ResponseEntity<ResponseStructure<ProductionHouse>> deleteProductionHouse(long houseId){
		 
		ProductionHouse house=houseDao.deleteProductionHouse(houseId);
		if(house!=null) {
			ResponseStructure<ProductionHouse> structure = new ResponseStructure<ProductionHouse>();
			structure.setMessage("ProductionHouse deleted successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(house);
			return new ResponseEntity<ResponseStructure<ProductionHouse>>(structure, HttpStatus.FOUND);
		}else {
//			ProductionHouse id not found
			throw new ProductionNotFoundByIdException("Failed to delete ProductionHouse!!");
		}
	}
	public ResponseEntity<ResponseStructure<ProductionHouse>> getProductionHouse(long houseId){
		 
			ProductionHouse house=houseDao.findProductionHouse(houseId);
			if(house!=null) {
				ResponseStructure<ProductionHouse> structure = new ResponseStructure<ProductionHouse>();
				structure.setMessage("ProductionHouse fetched successfully");
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(house);
				return new ResponseEntity<ResponseStructure<ProductionHouse>>(structure, HttpStatus.FOUND);
			}else {
//				ProductionHouse id not found
				throw new ProductionNotFoundByIdException("Failed to fetch ProductionHouse!!");
			}
			
	}
}
