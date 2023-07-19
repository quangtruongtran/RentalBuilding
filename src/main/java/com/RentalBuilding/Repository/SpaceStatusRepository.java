package com.RentalBuilding.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RentalBuilding.Entity.SpaceStatus;

public interface SpaceStatusRepository extends JpaRepository<SpaceStatus, Long> {
	SpaceStatus findBySpaceStatusName(String name);
}
