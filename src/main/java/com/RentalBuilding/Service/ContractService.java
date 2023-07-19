package com.RentalBuilding.Service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;

import com.RentalBuilding.DTO.ContractDTO;
import com.RentalBuilding.Entity.ContractEntity;

public interface ContractService {

	ContractEntity findByContractCode(String contractCode);
	ContractDTO save(ContractDTO contractDTO);
	ContractDTO findById(Long id);
	void deleteContract(Long id);
	List<ContractDTO> getListContractsWithPaging(Pageable pageable);
	List<ContractDTO> getListContracts();
	List<ContractDTO> findAllWithAutho(String userCode);
	List<ContractDTO> findAllWithAuthoAndPaging(Integer limit, Integer offset, String userCode);
	Set<ContractDTO> searchByField(String contractCode,  String contractDateStart, String contractDateEnd, String spaceCode);
}
