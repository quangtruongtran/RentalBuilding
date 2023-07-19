package com.RentalBuilding.Converter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.RentalBuilding.DTO.CustomerDTO;
import com.RentalBuilding.Entity.ContractEntity;
import com.RentalBuilding.Entity.CustomerEntity;
import com.RentalBuilding.Entity.UserEntity;

public class CustomerConverter extends Converter {
	public static CustomerEntity DTOtoEntity(CustomerDTO customer) {
		CustomerEntity obj = new CustomerEntity();
		obj = modelMapper.map(customer, CustomerEntity.class);
		return obj;
	}
	
	public static CustomerDTO EntitytoDTO(CustomerEntity customer) {
		CustomerDTO obj = new CustomerDTO();
		obj = modelMapper.map(customer, CustomerDTO.class);
		Set<Long> userIds = new HashSet<>();
		Map<String, String> users = new HashMap<>();
		if(customer.getCustomerStatus()!=null) {
		if(customer.getCustomerStatus().equals("Nhu cầu thuê")) {
			for (UserEntity item : customer.getUsers()) {
				userIds.add(item.getId());
				users.put(item.getUserCode(), item.getUserName());
			}
		}
		else {
			for (ContractEntity item : customer.getContracts()) {
				userIds.add(item.getUser().getId());
				users.put(item.getUser().getUserCode(), item.getUser().getUserName());
			}
		}
		}
		obj.setUserIds(userIds);
		obj.setUsers(users);
		for (ContractEntity item : customer.getContracts()) {
			obj.getListContractsCode().add(item.getContractCode());
		}
		return obj;
	}
	
	public static void updateEntity(CustomerEntity customer, CustomerDTO customerDTO) {
		customer.setCustomerName(customerDTO.getCustomerName());
		customer.setCustomerIdentifyNumber(customerDTO.getCustomerIdentifyNumber());
		customer.setCustomerEmail(customerDTO.getCustomerEmail());
		customer.setCustomerPhone(customerDTO.getCustomerPhone());
		customer.setCustomerDateOfBirth(customerDTO.getCustomerDateOfBirth());
		customer.setCustomerAddress(customerDTO.getCustomerAddress());
		customer.setCustomerStatus(customerDTO.getCustomerStatus());
	}
}
