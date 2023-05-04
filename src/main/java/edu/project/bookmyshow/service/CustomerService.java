package edu.project.bookmyshow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.bookmyshow.dao.CustomerDao;
import edu.project.bookmyshow.dto.CustomerDto;
import edu.project.bookmyshow.entity.Customer;
import edu.project.bookmyshow.util.ResponseStructure;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CustomerDto customerDto;

	public ResponseEntity<ResponseStructure<CustomerDto>> saveCustomer(Customer customer) {
		ResponseStructure<CustomerDto> responseStructure = new ResponseStructure<>();
		Customer dbcustomer = customerDao.saveCustomer(customer);
		if (dbcustomer != null) {
			customerDto.setCustomerId(customer.getCustomerId());
			customerDto.setCustomerName(customer.getCustomerName());
			customerDto.setCustomrPhoneNumber(customer.getCustomrPhoneNumber());
			customerDto.setCustomerEmail(customer.getCustomerEmail());
			responseStructure.setMessage("customer saved successfully");
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setData(customerDto);
			return new ResponseEntity<ResponseStructure<CustomerDto>>(responseStructure, HttpStatus.CREATED);
		}
		return null;

	}

	public ResponseEntity<ResponseStructure<CustomerDto>> deleteCustomer(long id) {
		ResponseStructure<CustomerDto> responseStructure = new ResponseStructure<>();
		Customer dbcustomer = customerDao.deleteCustomer(id);
		if (dbcustomer != null) {
			customerDto.setCustomerId(dbcustomer.getCustomerId());
			customerDto.setCustomerName(dbcustomer.getCustomerName());
			customerDto.setCustomrPhoneNumber(dbcustomer.getCustomrPhoneNumber());
			customerDto.setCustomerEmail(dbcustomer.getCustomerEmail());
			responseStructure.setMessage("customer deleted successfully");
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setData(customerDto);
			return new ResponseEntity<ResponseStructure<CustomerDto>>(responseStructure, HttpStatus.OK);
		}
		return null;
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> getCustomerById(long id) {
		ResponseStructure<CustomerDto> responseStructure = new ResponseStructure<>();
		Customer dbcustomer = customerDao.getCustomerById(id);
		if (dbcustomer != null) {
			customerDto.setCustomerId(dbcustomer.getCustomerId());
			customerDto.setCustomerName(dbcustomer.getCustomerName());
			customerDto.setCustomrPhoneNumber(dbcustomer.getCustomrPhoneNumber());
			customerDto.setCustomerEmail(dbcustomer.getCustomerEmail());
			responseStructure.setMessage("customer fetched successfully");
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setData(customerDto);
			return new ResponseEntity<ResponseStructure<CustomerDto>>(responseStructure, HttpStatus.OK);
		}
		return null;
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> updateCustomer(long id, Customer customer) {
		ResponseStructure<CustomerDto> responseStructure = new ResponseStructure<>();
		Customer dbcustomer = customerDao.updateCustomer(id, customer);
		if(dbcustomer!=null) {
			customerDto.setCustomerId(dbcustomer.getCustomerId());
			customerDto.setCustomerName(dbcustomer.getCustomerName());
			customerDto.setCustomrPhoneNumber(dbcustomer.getCustomrPhoneNumber());
			customerDto.setCustomerEmail(dbcustomer.getCustomerEmail());
			responseStructure.setMessage("customer updated successfully");
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setData(customerDto);
			return new ResponseEntity<ResponseStructure<CustomerDto>>(responseStructure, HttpStatus.OK);
		}
		return null;	
		}
	}

