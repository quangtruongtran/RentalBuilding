package com.RentalBuilding.ServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.RentalBuilding.Converter.CustomerConverter;
import com.RentalBuilding.CustomRepository.CustomerRepositoryCustom;
import com.RentalBuilding.DTO.CustomerDTO;
import com.RentalBuilding.Entity.ContractEntity;
import com.RentalBuilding.Entity.CustomerEntity;
import com.RentalBuilding.Entity.UserEntity;
import com.RentalBuilding.Exception.InvalidPageException;
import com.RentalBuilding.Repository.CustomerRepository;
import com.RentalBuilding.Repository.UserRepository;
import com.RentalBuilding.Service.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CustomerRepositoryCustom customerRepositoryCustom;

	@Override
	public CustomerEntity findByCustomerIdentifyNumber(String customerIdentifyNumber) {
		return customerRepository.findByCustomerIdentifyNumber(customerIdentifyNumber);
	}

	@Override
	public CustomerDTO save(CustomerDTO customerDTO) {
		if (customerDTO.getId() == null) {
			CustomerEntity customer = CustomerConverter.DTOtoEntity(customerDTO);
			for (Long id : customerDTO.getUserIds()) {
				customer.getUsers().add(userRepository.findById(id).get());
			}
				return CustomerConverter.EntitytoDTO(customerRepository.save(customer));
		} else if (customerDTO.getId() != null) {
			Optional<CustomerEntity> existedCustomer = customerRepository.findById(customerDTO.getId());
			CustomerConverter.updateEntity(existedCustomer.get(), customerDTO);
			existedCustomer.get().getUsers().clear();
			for (Long id : customerDTO.getUserIds()) {
				existedCustomer.get().getUsers().add(userRepository.findById(id).get());
			}
			
			return CustomerConverter.EntitytoDTO(customerRepository.save(existedCustomer.get()));
		}
		return null;
	}

	@Override
	public CustomerDTO findById(Long id) {
		Optional<CustomerEntity> customer = customerRepository.findById(id);
		if(!customer.isPresent()) {
			return null;
		} 
		return CustomerConverter.EntitytoDTO(customer.get());
	}

	@Override
	public void deleteCustomer(Long id) {
		customerRepository.deleteById(id);
	}

	@Override
	public List<CustomerDTO> getListCustomersWithPaging(Pageable pageable) {
		List<CustomerDTO> results = new ArrayList<>();
		for (CustomerEntity customer : customerRepository.findAll(pageable).getContent()) {
			results.add(CustomerConverter.EntitytoDTO(customer));
		}
		return results;
	}

	@Override
	public List<CustomerDTO> getListCustomers() {
		List<CustomerDTO> results = new ArrayList<>();
		for (CustomerEntity customer : customerRepository.findAll()) {
			results.add(CustomerConverter.EntitytoDTO(customer));
		}
		return results;
	}
	
	@Override
	public Map<Long,CustomerDTO> getListCustomerWithUser(String userCode) {
		UserEntity user = userRepository.findByUserCode(userCode);
		Map<Long,CustomerDTO> list = new HashMap<>();
		for (ContractEntity item : user.getContracts()) {
			list.put(item.getCustomer().getId(),CustomerConverter.EntitytoDTO(item.getCustomer()));
		}
		for(CustomerEntity item: user.getCustomers()) {
			list.put(item.getId(),CustomerConverter.EntitytoDTO(item));
	}
		return list;
}
	@Override
	public List<CustomerDTO> getListCustomerWithUserAndPaging(Integer limit, Integer Offset, String userCode) {
		Map<Long,CustomerDTO> list = getListCustomerWithUser(userCode);
		Collection<CustomerDTO> collection = list.values();	
		List<CustomerDTO> temp = new ArrayList<CustomerDTO>(collection);
		List<CustomerDTO> result = new ArrayList<>();
		if(((temp.size()/limit)+1)<Offset+1) throw new InvalidPageException();
		for(int i =  Offset * limit; i < (Offset * limit) +limit; i++) {
			if(i==temp.size()) {
				break;
			}
			result.add(temp.get(i));
		}
		return result;
}

	@Override
	public Set<CustomerDTO> searchByField(String customerName, String customerIdentifyNumber, String customerStatus) {
		return customerRepositoryCustom.searchByField(customerName, customerIdentifyNumber, customerStatus);
	}

}
