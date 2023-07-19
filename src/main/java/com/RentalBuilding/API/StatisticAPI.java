package com.RentalBuilding.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RentalBuilding.DTO.ResponseDTO;
import com.RentalBuilding.Service.StatisticService;

@RestController
@RequestMapping("/api/statistics")
public class StatisticAPI {
	@Autowired
	private StatisticService statisticService;
	@GetMapping
	public ResponseEntity<ResponseDTO> getStatistics(){
		ResponseDTO responseDTO = new ResponseDTO(null, statisticService.getStatistics());
		return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
	}
}
