package com.RentalBuilding.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.RentalBuilding.Entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findByUserCode(String userCode);
	UserEntity findByUserEmail(String userEmail);
	UserEntity findByUserName(String userEmail);
	@Query(value = "Select * from user where department_id = 3", nativeQuery = true)
	List<UserEntity> findAllTechStaff();
}
