package com.RentalBuilding.CustomRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.RentalBuilding.Converter.FloorConverter;
import com.RentalBuilding.Converter.UserConverter;
import com.RentalBuilding.DTO.FloorDTO;
import com.RentalBuilding.DTO.UserDTO;
import com.RentalBuilding.Entity.FloorEntity;
import com.RentalBuilding.Entity.UserEntity;

@Repository
public class UserRepositoryCustom {
	@PersistenceContext
    private EntityManager entityManager;
	public StringBuilder buildJoinClause(String userName, String userCode, String userEmail, String departmentCode) {
		StringBuilder joinquery = new StringBuilder();
		if(departmentCode != null) {
			joinquery.append(" left join department as d on u.department_id = d.id");
		}
		return joinquery;
	}
	
	public StringBuilder buildWhereClause(String userName, String userCode, String userEmail, String departmentCode) {
		StringBuilder joinquery = new StringBuilder(" where 1=1");
		if(userName != null) {
			joinquery.append(" and user_name = '"+userName+"'");
		}
		if(userCode != null) {
			joinquery.append(" and user_code = '"+userCode+"'");
		}
		if(userEmail != null) {
			joinquery.append(" and user_email = '"+userEmail+"'");
		}
		if(departmentCode != null) {
			joinquery.append(" and department_code = '"+departmentCode+"'");
		}
		return joinquery;
	}
	
	public Set<UserDTO> searchByField(String userName, String userCode, String userEmail, String departmentCode){
		Query query = entityManager.createNativeQuery("Select * from user as u" + buildJoinClause(userName,userCode,userEmail,departmentCode) 
		+ buildWhereClause(userName,userCode,userEmail,departmentCode), UserEntity.class);
		List<UserEntity> list = query.getResultList();
		Set<UserDTO> result = new HashSet<>();
		for (UserEntity user : list) {
			result.add(UserConverter.EntitytoDTO(user));
		}
		return result;
	}
}
