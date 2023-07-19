package com.RentalBuilding.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RentalBuilding.Entity.FloorEntity;

public interface FloorRepository extends JpaRepository<FloorEntity, Long> {
	FloorEntity findByFloorName(String floorName);
}
