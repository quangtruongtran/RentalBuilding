package com.RentalBuilding.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.RentalBuilding.Entity.SpaceEntity;

public interface SpaceRepository extends JpaRepository<SpaceEntity, Long> {
	SpaceEntity findBySpaceCode(String spaceCode);
	@Query(value = "Select * from space where floor_id = :id ", nativeQuery = true)
	List<SpaceEntity> findByFloorId(Long id);
	@Query(value = "select * from space as s right join contract as c on c.space_id = s.id where "
			+ "DATEDIFF(contract_date_end, CURDATE()) < 10 and DATEDIFF(contract_date_end, CURDATE())>0;", nativeQuery = true )
	List<SpaceEntity> getSpacesThatContractComingToEnd();
}
