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

import com.RentalBuilding.DTO.ContractDTO;
import com.RentalBuilding.DTO.MyUser;
import com.RentalBuilding.DTO.ResponseDTO;
import com.RentalBuilding.Exception.ExistedException;
import com.RentalBuilding.Exception.FieldRequiredException;
import com.RentalBuilding.Exception.NotExistedException;
import com.RentalBuilding.Service.ContractService;
import com.RentalBuilding.Utils.Validate;

@RestController
@RequestMapping("/api/contract")
public class ContractAPI {
	
	@Autowired
	private ContractService contractService;
	
	@PostMapping
	public ResponseEntity<ResponseDTO> saveContract(@RequestBody ContractDTO contractDTO) {
		if(!Validate.ContractValidate(contractDTO)) {
			throw new FieldRequiredException();
		}
		if (contractService.findByContractCode(contractDTO.getContractCode()) != null) {
			throw new ExistedException();
		} else {
			ResponseDTO result = new ResponseDTO(null, contractService.save(contractDTO));
			return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseDTO> updateContract(@RequestBody ContractDTO contractDTO, @PathVariable Long id) {
		if(!Validate.ContractValidate(contractDTO)) {
			throw new FieldRequiredException();
		}
		if (contractService.findById(id) == null) {
			throw new NotExistedException();
		} else {
			contractDTO.setId(id);
			ResponseDTO result = new ResponseDTO(null, contractService.save(contractDTO));
			return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
		}

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseDTO> deleteContract(@PathVariable Long id) {
		if (contractService.findById(id) == null) {
			throw new NotExistedException();
		} else {
			contractService.deleteContract(id);
			ResponseDTO result = new ResponseDTO(null);
			result.setData("");
			return new ResponseEntity<ResponseDTO>(result,HttpStatus.OK);
		}

	}
	
	@GetMapping("/getAll")
	public ResponseEntity<ResponseDTO> getListContractS(@RequestParam(value = "page", required = false) Integer page, 
			@RequestParam(value = "limit", required = false) Integer limit){
		ResponseDTO result = new ResponseDTO();
		if(page == null && limit == null) {
			result.setError(null);
			result.setData(contractService.getListContracts());
		} else {
			Pageable pageable = PageRequest.of(page - 1, limit);
			result.setError(null);
			result.setData(contractService.getListContractsWithPaging(pageable));
		}
		return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<ResponseDTO> getContractById(@PathVariable Long id){
		if (contractService.findById(id) == null) {
			throw new NotExistedException();
		}
		ResponseDTO result = new ResponseDTO(null, contractService.findById(id));
		return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
	}
	
	@GetMapping("/getAllWithAutho")
	public ResponseEntity<ResponseDTO> getAllWithAutho(@RequestParam(value = "page", required = false) Integer page, 
			@RequestParam(value = "limit", required = false) Integer limit){
		MyUser myUser = (MyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ResponseDTO result = new ResponseDTO();
		if(page == null && limit == null) {
			result.setError(null);
			result.setData(contractService.findAllWithAutho(myUser.getUserCode()));
		} else {
			result.setError(null);
			result.setData(contractService.findAllWithAuthoAndPaging(limit,page-1,myUser.getUserCode()));
		}
		return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
	}
	
	@GetMapping("/searchByField")
	public ResponseEntity<ResponseDTO> searchByField(@RequestParam(value = "contractCode", required =false) String contractCode, 
			@RequestParam(value = "contractDateStart", required =false)  String contractDateStart, 
			@RequestParam(value = "contractDateEnd", required =false)  String contractDateEnd,
			@RequestParam(value = "spaceCode", required =false) String spaceCode){
		ResponseDTO result = new ResponseDTO(null, contractService.searchByField(contractCode,contractDateStart,contractDateEnd,spaceCode));
		return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
	}
	
}
