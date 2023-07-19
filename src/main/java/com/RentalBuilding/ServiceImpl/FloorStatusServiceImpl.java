package com.RentalBuilding.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RentalBuilding.DTO.FloorStatusDTO;
import com.RentalBuilding.Entity.FloorStatus;
import com.RentalBuilding.Repository.FloorStatusRepository;
import com.RentalBuilding.Service.FloorStatusService;

@Service
public class FloorStatusServiceImpl implements FloorStatusService {

	@Autowired
	private FloorStatusRepository floorStatusRepository;
	
	@Override
	public List<FloorStatusDTO> getAll() {
		List<FloorStatusDTO> result = new ArrayList<>();
		for (FloorStatus floorStatus : floorStatusRepository.findAll()) {
			result.add(new FloorStatusDTO(floorStatus.getFloorStatusName()));
		}
		return result;
	}

}
