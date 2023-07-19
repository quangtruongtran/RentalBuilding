package com.RentalBuilding.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Pageable;

import com.RentalBuilding.DTO.CustomerDTO;
import com.RentalBuilding.Entity.CustomerEntity;

public interface CustomerService {

	CustomerEntity findByCustomerIdentifyNumber(String customerIdentifyNumber);

	CustomerDTO save(CustomerDTO customer);

	CustomerDTO findById(Long id);

	void deleteCustomer(Long id);

	List<CustomerDTO> getListCustomersWithPaging(Pageable pageable);

	List<CustomerDTO> getListCustomers();
	
	Map<Long,CustomerDTO> getListCustomerWithUser(String userCode);
	public List<CustomerDTO> getListCustomerWithUserAndPaging(Integer limit, Integer Offset, String userCode);
	Set<CustomerDTO> searchByField(String customerName,  String customerIdentifyNumber, String customerStatus);

}
