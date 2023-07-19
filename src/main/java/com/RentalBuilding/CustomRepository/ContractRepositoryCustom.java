package com.RentalBuilding.CustomRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.RentalBuilding.Converter.ContractConverter;
import com.RentalBuilding.DTO.ContractDTO;
import com.RentalBuilding.Entity.ContractEntity;

@Repository
public class ContractRepositoryCustom {
	@PersistenceContext
    private EntityManager entityManager;

	public StringBuilder buildJoinClause(String contractCode,  String contractDateStart, String contractDateEnd, String spaceCode) {
		StringBuilder joinquery = new StringBuilder();
		if(spaceCode != null) {
			joinquery.append(" left join space as s on c.space_id = s.id");
		}
		return joinquery;
	}
	
	public StringBuilder buildWhereClause(String contractCode,  String contractDateStart, String contractDateEnd, String spaceCode) {
		StringBuilder joinquery = new StringBuilder(" where 1=1");
		if(contractCode != null) {
			joinquery.append(" and contract_code = '"+contractCode+"'");
		}
		if(contractDateStart != null) {
			joinquery.append(" and contract_date_start = '"+contractDateStart+"'");
		}
		if(contractDateEnd != null) {
			joinquery.append(" and contract_date_end = '"+contractDateEnd+"'");
		}
		if(spaceCode != null) {
			joinquery.append(" and space_code = '"+spaceCode+"'");
		}
		return joinquery;
	}
	
	public Set<ContractDTO> searchByField(String contractCode,  String contractDateStart, String contractDateEnd, String spaceCode){
		Query query = entityManager.createNativeQuery("Select * from contract as c" + buildJoinClause(contractCode,contractDateStart,contractDateEnd,spaceCode) 
		+ buildWhereClause(contractCode,contractDateStart,contractDateEnd,spaceCode), ContractEntity.class);
		List<ContractEntity> list = query.getResultList();
		Set<ContractDTO> result = new HashSet<>();
		for (ContractEntity space : list) {
			result.add(ContractConverter.EntitytoDTO(space));
			System.out.println(space.getContractDateEnd());
			System.out.println(space.getContractDateStart());
		}
		return result;
	}
}
