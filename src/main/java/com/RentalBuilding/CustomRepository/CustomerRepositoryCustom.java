package com.RentalBuilding.CustomRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.RentalBuilding.Converter.CustomerConverter;
import com.RentalBuilding.DTO.CustomerDTO;
import com.RentalBuilding.Entity.CustomerEntity;

@Repository
public class CustomerRepositoryCustom {
	@PersistenceContext
    private EntityManager entityManager;

	
	public StringBuilder buildWhereClause(String customerName,  String customerIdentifyNumber, String customerStatus) {
		StringBuilder joinquery = new StringBuilder(" where 1=1");
		if(customerName != null) {
			joinquery.append(" and customer_name = '"+customerName+"'");
		}
		if(customerIdentifyNumber != null) {
			joinquery.append(" and customer_identify_number = '"+customerIdentifyNumber+"'");
		}
		if(customerStatus != null) {
			joinquery.append(" and customer_status = '"+customerStatus+"'");
		}
		return joinquery;
	}
	
	public Set<CustomerDTO> searchByField(String customerName,  String customerIdentifyNumber, String customerStatus){
		Query query = entityManager.createNativeQuery("Select * from customer as c"  
		+ buildWhereClause(customerName,customerIdentifyNumber,customerStatus), CustomerEntity.class);
		List<CustomerEntity> list = query.getResultList();
		Set<CustomerDTO> result = new HashSet<>();
		for (CustomerEntity customer : list) {
			result.add(CustomerConverter.EntitytoDTO(customer));
		}
		return result;
	}
}
