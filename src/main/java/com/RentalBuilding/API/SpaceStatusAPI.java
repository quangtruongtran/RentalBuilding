package com.RentalBuilding.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RentalBuilding.DTO.ResponseDTO;
import com.RentalBuilding.Service.SpaceStatusService;

@RestController
@RequestMapping("/api/spaceStatus")
public class SpaceStatusAPI {
	@Autowired
	private SpaceStatusService spaceStatusService;
	
	@GetMapping("/getAll")
	public ResponseEntity<ResponseDTO> getListSpaceStatuss(){
		ResponseDTO result = new ResponseDTO();
			result.setError(null);
			result.setData(spaceStatusService.getAll());
		return new ResponseEntity<ResponseDTO>(result, HttpStatus.OK);
	}
}
