package com.RentalBuilding.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RentalBuilding.DTO.SpaceStatusDTO;
import com.RentalBuilding.Entity.SpaceStatus;
import com.RentalBuilding.Repository.SpaceStatusRepository;
import com.RentalBuilding.Service.SpaceStatusService;

@Service
public class SpaceStatusServiceImpl implements SpaceStatusService {
	@Autowired
	private SpaceStatusRepository spaceStatusRepository;
	@Override
	public List<SpaceStatusDTO> getAll() {
		List<SpaceStatusDTO> result = new ArrayList<>();
		for (SpaceStatus spaceStatus : spaceStatusRepository.findAll()) {
			result.add(new SpaceStatusDTO(spaceStatus.getSpaceStatusName()));
		}
		return result;
	}

}
