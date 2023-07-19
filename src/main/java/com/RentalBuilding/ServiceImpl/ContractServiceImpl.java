package com.RentalBuilding.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.RentalBuilding.Converter.ContractConverter;
import com.RentalBuilding.CustomRepository.ContractRepositoryCustom;
import com.RentalBuilding.DTO.ContractDTO;
import com.RentalBuilding.Entity.ContractEntity;
import com.RentalBuilding.Repository.ContractRepository;
import com.RentalBuilding.Repository.CustomerRepository;
import com.RentalBuilding.Repository.SpaceRepository;
import com.RentalBuilding.Repository.UserRepository;
import com.RentalBuilding.Service.ContractService;

@Service
@Transactional
public class ContractServiceImpl implements ContractService {

	@Autowired
	private ContractRepository contractRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SpaceRepository spaceRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ContractRepositoryCustom contractRepositoryCustom;
	
	@Override
	public ContractEntity findByContractCode(String contractCode) {
		  return contractRepository.findByContractCode(contractCode);
	}

	@Override
	public ContractDTO save(ContractDTO contractDTO) {
		if (contractDTO.getId() == null) {
			ContractEntity contract = ContractConverter.DTOtoEntity(contractDTO);
			contract.setUser(userRepository.findById(contractDTO.getUserId()).get());
			contract.setCustomer(customerRepository.findById(contractDTO.getCustomerId()).get());
			contract.setSpace(spaceRepository.findBySpaceCode(contractDTO.getSpaceCode()));
			contract.getCustomer().getUsers().remove(userRepository.findById(contractDTO.getUserId()).get());
			contract.getCustomer().setCustomerStatus("Đang thuê");
				return ContractConverter.EntitytoDTO(contractRepository.save(contract));
		} else if (contractDTO.getId() != null) {
			Optional<ContractEntity> existedContract = contractRepository.findById(contractDTO.getId());
			ContractConverter.updateEntity(existedContract.get(), contractDTO);
			existedContract.get().setUser(userRepository.findById(contractDTO.getUserId()).get());
			existedContract.get().setCustomer(customerRepository.findById(contractDTO.getCustomerId()).get());
			existedContract.get().setSpace(spaceRepository.findBySpaceCode(contractDTO.getSpaceCode()));
			return ContractConverter.EntitytoDTO(contractRepository.save(existedContract.get()));
		}

		return null;
	}

	@Override
	public ContractDTO findById(Long id) {
		Optional<ContractEntity> contract = contractRepository.findById(id);
		if(!contract.isPresent()) {
			return null;
		} 
		return ContractConverter.EntitytoDTO(contract.get());
	}

	@Override
	public void deleteContract(Long id) {
		contractRepository.deleteById(id);
	}

	@Override
	public List<ContractDTO> getListContractsWithPaging(Pageable pageable) {
		List<ContractDTO> results = new ArrayList<>();
		for (ContractEntity contract : contractRepository.findAll(pageable).getContent()) {
			results.add(ContractConverter.EntitytoDTO(contract));
		}
		return results;
	}

	@Override
	public List<ContractDTO> getListContracts() {
		List<ContractDTO> results = new ArrayList<>();
		for (ContractEntity contract : contractRepository.findAll()) {
			results.add(ContractConverter.EntitytoDTO(contract));
		}
		return results;
	}

	@Override
	public List<ContractDTO> findAllWithAutho(String userCode) {
		List<ContractDTO> list = new ArrayList<>();
		for (ContractEntity item : contractRepository.findAllWithAutho(userCode)) {
			list.add(ContractConverter.EntitytoDTO(item));
		}
		return list;	
	}

	@Override
	public List<ContractDTO> findAllWithAuthoAndPaging(Integer limit, Integer offset, String userCode) {
		List<ContractDTO> list = new ArrayList<>();
		for (ContractEntity item : contractRepository.findAllWithAuthoAndPaging(limit, offset, userCode)) {
			list.add(ContractConverter.EntitytoDTO(item));
		}
		return list;	
	}

	@Override
	public Set<ContractDTO> searchByField(String contractCode, String contractDateStart, String contractDateEnd,
			String spaceCode) {
		return contractRepositoryCustom.searchByField(contractCode, contractDateStart, contractDateEnd, spaceCode);
	}
	


}
