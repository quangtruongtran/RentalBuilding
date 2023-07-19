package com.RentalBuilding.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RentalBuilding.DTO.FloorDTO;
import com.RentalBuilding.DTO.ResponseDTO;
import com.RentalBuilding.Exception.ExistedException;
import com.RentalBuilding.Exception.FieldRequiredException;
import com.RentalBuilding.Exception.NotExistedException;
import com.RentalBuilding.Service.FloorService;
import com.RentalBuilding.Utils.Validate;

@RestController
@RequestMapping("/api/floor")
public class FloorAPI {

	@Autowired
	private FloorService floorService;

	@PostMapping
	public ResponseEntity<ResponseDTO> saveFloor(@RequestBody FloorDTO floor) {
		if(!Validate.FloorValidate(floor)) {
			throw new FieldRequiredException();
		}
		if (floorService.findByFloorName(floor.getFloorName()) != null) {
			throw new ExistedException();
		} else {
			ResponseDTO result = new ResponseDTO(null, floorService.save(floor));
			return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseDTO> updateFloor(@RequestBody FloorDTO floor, @PathVariable Long id) {
		if(!Validate.FloorValidate(floor)) {
			throw new FieldRequiredException();
		}
		if (floorService.findById(id) == null) {
			throw new NotExistedException();
		} else {
			floor.setId(id);
			ResponseDTO result = new ResponseDTO(null, floorService.save(floor));
			return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
		}

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseDTO> deleteFloor(@PathVariable Long id) {
		if (floorService.findById(id) == null) {
			throw new NotExistedException();
		} else {
			floorService.deleteFloor(id);
			ResponseDTO result = new ResponseDTO(null);
			result.setData("");
			return new ResponseEntity<ResponseDTO>(result,HttpStatus.OK);
		}

	}
	
	@GetMapping("/getAll")
	public ResponseEntity<ResponseDTO> getListFloorS(@RequestParam(value = "page", required = false) Integer page, 
			@RequestParam(value = "limit", required = false) Integer limit){
		ResponseDTO result = new ResponseDTO();
		if(page == null && limit == null) {
			result.setError(null);
			result.setData(floorService.getListFloors());
		} else {
			Pageable pageable = PageRequest.of(page - 1, limit);
			result.setError(null);
			result.setData(floorService.getListFloorsWithPaging(pageable));
		}
		return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<ResponseDTO> getContractById(@PathVariable Long id){
		if (floorService.findById(id) == null) {
			throw new NotExistedException();
		}
		ResponseDTO result = new ResponseDTO(null, floorService.findById(id));
		return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
	}
	
	@GetMapping("/searchByField")
	public ResponseEntity<ResponseDTO> searchByField(@RequestParam(value = "floorName", required =false) String floornName, 
			@RequestParam(value = "floorStatus", required =false) String floorStatus, 
			@RequestParam(value = "floorType", required =false) String floorType){
		ResponseDTO result = new ResponseDTO(null, floorService.searchByField(floornName, floorStatus, floorType));
		return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
	}

}
