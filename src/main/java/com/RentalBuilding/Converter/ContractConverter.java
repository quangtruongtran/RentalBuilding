package com.RentalBuilding.Converter;

import com.RentalBuilding.DTO.ContractDTO;
import com.RentalBuilding.Entity.ContractEntity;

public class ContractConverter extends Converter {
	public static ContractEntity DTOtoEntity(ContractDTO contract) {
		ContractEntity obj = new ContractEntity();
		obj = modelMapper.map(contract, ContractEntity.class);
		return obj;
	}
	
	public static ContractDTO EntitytoDTO(ContractEntity contract) {
		ContractDTO obj = modelMapper.map(contract, ContractDTO.class);
		obj.setCustomerId(contract.getCustomer().getId());
		obj.setSpaceCode(contract.getSpace().getSpaceCode());
		obj.setUserId(contract.getUser().getId());
		obj.setCustomerIdentifyNumber(contract.getCustomer().getCustomerIdentifyNumber());
		obj.setCustomerName(contract.getCustomer().getCustomerName());
		obj.setUserName(contract.getUser().getUserName());
		obj.setUserCode(contract.getUser().getUserCode());
		return obj;
	}
	
	public static void updateEntity(ContractEntity contract, ContractDTO contractDTO) {
		contract.setContractCode(contractDTO.getContractCode());
		contract.setContractPeriod(contractDTO.getContractPeriod());
		contract.setContractDateStart(contractDTO.getContractDateStart());
		contract.setContractDateEnd(contractDTO.getContractDateEnd());
		contract.setContractDeposit(contractDTO.getContractDeposit());
		contract.setContractTaxCode(contractDTO.getContractTaxCode());
		contract.setContractContent(contractDTO.getContractContent());
		contract.setContractCompensation(contractDTO.getContractCompensation());
	}
}
