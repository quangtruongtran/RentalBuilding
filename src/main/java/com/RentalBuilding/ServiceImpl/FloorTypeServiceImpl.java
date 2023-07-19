package com.RentalBuilding.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RentalBuilding.DTO.FloorTypeDTO;
import com.RentalBuilding.Entity.FloorType;
import com.RentalBuilding.Repository.FloorTypeRepository;
import com.RentalBuilding.Service.FloorTypeService;

@Service
public class FloorTypeServiceImpl implements FloorTypeService {

	@Autowired
	private FloorTypeRepository floorTypeRepository;

	@Override
	public List<FloorTypeDTO> getAll() {
		List<FloorTypeDTO> result = new ArrayList<>();
		for (FloorType floorType : floorTypeRepository.findAll()) {
			result.add(new FloorTypeDTO(floorType.getFloorTypeName()));
		}
		return result;
	}
}
