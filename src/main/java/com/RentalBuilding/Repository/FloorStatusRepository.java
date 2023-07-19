package com.RentalBuilding.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RentalBuilding.Entity.FloorStatus;

public interface FloorStatusRepository extends JpaRepository<FloorStatus, Long> {
	FloorStatus findByFloorStatusName(String name);
}
