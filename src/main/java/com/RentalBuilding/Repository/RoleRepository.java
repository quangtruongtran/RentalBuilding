package com.RentalBuilding.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RentalBuilding.Entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
	RoleEntity findByRoleCode(String code);
}
