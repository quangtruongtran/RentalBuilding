package com.RentalBuilding.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RentalBuilding.DTO.SpaceTypeDTO;
import com.RentalBuilding.Entity.SpaceType;
import com.RentalBuilding.Repository.SpaceTypeRepository;
import com.RentalBuilding.Service.SpaceTypeService;

@Service
public class SpaceTypeServiceImpl implements SpaceTypeService {
	@Autowired
	private SpaceTypeRepository spaceTypeRepository;

	@Override
	public List<SpaceTypeDTO> getAll() {
		List<SpaceTypeDTO> result = new ArrayList<>();
		for (SpaceType spaceType : spaceTypeRepository.findAll()) {
			result.add(new SpaceTypeDTO(spaceType.getSpaceTypeName()));
		}
		return result;
	}
}
