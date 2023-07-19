package com.RentalBuilding.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RentalBuilding.Entity.FloorType;

public interface FloorTypeRepository extends JpaRepository<FloorType, Long> {
	FloorType findByFloorTypeName(String name);
}
