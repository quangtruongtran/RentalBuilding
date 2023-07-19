package com.RentalBuilding.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RentalBuilding.DTO.ResponseDTO;
import com.RentalBuilding.Service.SpaceTypeService;

@RestController
@RequestMapping("/api/spaceType")
public class SpaceTypeAPI {
	@Autowired
	private SpaceTypeService spaceTypeService;
	
	@GetMapping("/getAll")
	public ResponseEntity<ResponseDTO> getListSpaceTypes(){
		ResponseDTO result = new ResponseDTO();
			result.setError(null);
			result.setData(spaceTypeService.getAll());
		return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
	}
}
