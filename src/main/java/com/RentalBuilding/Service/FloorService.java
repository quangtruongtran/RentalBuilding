package com.RentalBuilding.Service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;

import com.RentalBuilding.DTO.FloorDTO;
import com.RentalBuilding.Entity.FloorEntity;

public interface FloorService {
	FloorDTO save(FloorDTO floor);
	FloorEntity findByFloorName(String name);
	FloorDTO findById(Long id);
	void deleteFloor(Long id);
	List<FloorDTO> getListFloors();
	List<FloorDTO> getListFloorsWithPaging(Pageable pageable);
	Set<FloorDTO> searchByField(String floorName, String floorStatus, String floorType);
}
