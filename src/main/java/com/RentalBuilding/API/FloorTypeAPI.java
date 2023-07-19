package com.RentalBuilding.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RentalBuilding.DTO.ResponseDTO;
import com.RentalBuilding.Service.FloorTypeService;

@RestController
@RequestMapping("/api/floorType")
public class FloorTypeAPI {
	@Autowired
	private FloorTypeService floorTypeService;
	
	@GetMapping("/getAll")
	public ResponseEntity<ResponseDTO> getListFloorTypes(){
		ResponseDTO result = new ResponseDTO();
			result.setError(null);
			result.setData(floorTypeService.getAll());
		return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
	}
}
