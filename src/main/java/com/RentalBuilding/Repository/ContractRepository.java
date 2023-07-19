package com.RentalBuilding.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.RentalBuilding.Entity.ContractEntity;

public interface ContractRepository extends JpaRepository<ContractEntity, Long> {
	ContractEntity findByContractCode(String contractCode);
	@Query(value = "Select * from contract where user_id = :id", nativeQuery = true)
	List<ContractEntity> findByUserId(Long id);
	@Query(value = "Select c from ContractEntity c where c.user.userCode = ?1")
	List<ContractEntity> findAllWithAutho(String userCode);
	@Query(value = "Select * from contract as c left join user as u on c.user_id = u.id where user_code = ?3 limit ?2,?1", nativeQuery = true)
	List<ContractEntity> findAllWithAuthoAndPaging(Integer limit, Integer offset, String userCode);
}
