package com.RentalBuilding.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RentalBuilding.Entity.SpaceType;

public interface SpaceTypeRepository extends JpaRepository<SpaceType, Long> {
	SpaceType findBySpaceTypeName(String name);
}
