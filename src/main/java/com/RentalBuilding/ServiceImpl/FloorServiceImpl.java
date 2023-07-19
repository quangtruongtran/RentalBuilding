package com.RentalBuilding.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.RentalBuilding.Converter.FloorConverter;
import com.RentalBuilding.CustomRepository.FloorRepositoryCustom;
import com.RentalBuilding.DTO.FloorDTO;
import com.RentalBuilding.Entity.FloorEntity;
import com.RentalBuilding.Repository.FloorRepository;
import com.RentalBuilding.Repository.FloorStatusRepository;
import com.RentalBuilding.Repository.FloorTypeRepository;
import com.RentalBuilding.Service.FloorService;

@Service
@Transactional
public class FloorServiceImpl implements FloorService {

	@Autowired
	private FloorRepository floorRepository;
	@Autowired
	private FloorStatusRepository floorStatusRepository;
	@Autowired
	private FloorTypeRepository floorTypeRepository;
	@Autowired
	private FloorRepositoryCustom floorRepositoryCustom;

	@Override
	public FloorDTO save(FloorDTO floorDTO) {
		if (floorDTO.getId() == null) {
			FloorEntity floor = FloorConverter.FloorDTOtoEntity(floorDTO);
			floor.setFloorStatus(floorStatusRepository.findByFloorStatusName(floorDTO.getFloorStatusName()));
			floor.setFloorType(floorTypeRepository.findByFloorTypeName(floorDTO.getFloorTypeName()));
			return FloorConverter.FloorEntitytoDTO(floorRepository.save(floor));
		
		} else if (floorDTO.getId() != null) {
			Optional<FloorEntity> existedFloor = floorRepository.findById(floorDTO.getId());
			existedFloor.get().setFloorArea(floorDTO.getFloorArea());
			existedFloor.get().setFloorName(floorDTO.getFloorName());
			existedFloor.get()
					.setFloorStatus(floorStatusRepository.findByFloorStatusName(floorDTO.getFloorStatusName()));
			existedFloor.get().setFloorType(floorTypeRepository.findByFloorTypeName(floorDTO.getFloorTypeName()));
			return FloorConverter.FloorEntitytoDTO(floorRepository.save(existedFloor.get()));
		}
		return null;
	}

	@Override
	public FloorEntity findByFloorName(String name) {
		return floorRepository.findByFloorName(name);
	}


	@Override
	public void deleteFloor(Long id) {
		floorRepository.deleteById(id);
	}

	@Override
	public FloorDTO findById(Long id) {
		Optional<FloorEntity> floor = floorRepository.findById(id);
		if(!floor.isPresent()) {
			return null;
		} 
		return FloorConverter.FloorEntitytoDTO(floor.get());
	}

	@Override
	public List<FloorDTO> getListFloors() {
		List<FloorDTO> results = new ArrayList<>();
		for (FloorEntity item : floorRepository.findAll()) {
			results.add(FloorConverter.FloorEntitytoDTO(item));
		}
		return results;
	}

	@Override
	public List<FloorDTO> getListFloorsWithPaging(Pageable pageable) {
		List<FloorDTO> results = new ArrayList<>();
		for (FloorEntity item : floorRepository.findAll(pageable).getContent()) {
			results.add(FloorConverter.FloorEntitytoDTO(item));
		}
		return results;
	}

	@Override
	public Set<FloorDTO> searchByField(String floorName, String floorStatus, String floorType) {	
		return floorRepositoryCustom.searchByField(floorStatus, floorType, floorName);
	}
	


}
