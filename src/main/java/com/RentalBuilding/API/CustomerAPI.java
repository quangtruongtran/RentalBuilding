package com.RentalBuilding.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RentalBuilding.DTO.CustomerDTO;
import com.RentalBuilding.DTO.MyUser;
import com.RentalBuilding.DTO.ResponseDTO;
import com.RentalBuilding.Exception.ExistedException;
import com.RentalBuilding.Exception.FieldRequiredException;
import com.RentalBuilding.Exception.NotExistedException;
import com.RentalBuilding.Service.CustomerService;
import com.RentalBuilding.Utils.Validate;

@RestController
@RequestMapping("/api/customer")
public class CustomerAPI {
	@Autowired
	private CustomerService customService;

	@PostMapping
	public ResponseEntity<ResponseDTO> saveCustomer(@RequestBody CustomerDTO customer) {
		if(!Validate.CustomerValidate(customer)) {
			throw new FieldRequiredException();
		}
		if (customService.findByCustomerIdentifyNumber(customer.getCustomerIdentifyNumber()) != null) {
			throw new ExistedException();
		} else {
			ResponseDTO result = new ResponseDTO(null, customService.save(customer));
			return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseDTO> updateCustomer(@RequestBody CustomerDTO customer, @PathVariable Long id) {
		if(!Validate.CustomerValidate(customer)) {
			throw new FieldRequiredException();
		}
		if (customService.findById(id) == null) {
			throw new NotExistedException();
		} else {
			customer.setId(id);
			ResponseDTO result = new ResponseDTO(null, customService.save(customer));
			return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
		}

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseDTO> deleteCustomer(@PathVariable Long id) {
		if (customService.findById(id) == null) {
			throw new NotExistedException();
		} else {
			customService.deleteCustomer(id);
			ResponseDTO result = new ResponseDTO(null);
			result.setData("");
			return new ResponseEntity<ResponseDTO>(result,HttpStatus.OK);
		}

	}
	
	@GetMapping("/getAll")
	public ResponseEntity<ResponseDTO> getListCustomerS(@RequestParam(value = "page", required = false) Integer page, 
			@RequestParam(value = "limit", required = false) Integer limit){
		ResponseDTO result = new ResponseDTO();
		if(page == null && limit == null) {
			result.setError(null);
			result.setData(customService.getListCustomers());
		} else {
			Pageable pageable = PageRequest.of(page - 1, limit);
			result.setError(null);
			result.setData(customService.getListCustomersWithPaging(pageable));
		}
		return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<ResponseDTO> getCustomerById(@PathVariable Long id){
		if (customService.findById(id) == null) {
			throw new NotExistedException();
		}
		ResponseDTO result = new ResponseDTO(null, customService.findById(id));
		return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
	}
	
	@GetMapping("/getAllWithAutho")
	public ResponseEntity<ResponseDTO> getAllWithAutho(@RequestParam(value = "page", required = false) Integer page, 
			@RequestParam(value = "limit", required = false) Integer limit){
		MyUser myUser = (MyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ResponseDTO result = new ResponseDTO();
		if(page == null && limit == null) {
			result.setError(null);
			result.setData(customService.getListCustomerWithUser(myUser.getUserCode()));
		} else {
			result.setError(null);
			result.setData(customService.getListCustomerWithUserAndPaging(limit,page-1,myUser.getUserCode()));
		}
		return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
	}
	
	@GetMapping("/searchByField")
	public ResponseEntity<ResponseDTO> searchByField(@RequestParam(value = "customerName", required =false) String customerName, 
			@RequestParam(value = "customerIdentifyNumber", required =false)  String customerIdentifyNumber, 
			@RequestParam(value = "customerStatus", required =false)  String customerStatus){
		ResponseDTO result = new ResponseDTO(null, customService.searchByField(customerName, customerIdentifyNumber, customerStatus));
		return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
	}
}
