package com.RentalBuilding.CustomRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.RentalBuilding.Converter.FloorConverter;
import com.RentalBuilding.DTO.FloorDTO;
import com.RentalBuilding.Entity.FloorEntity;

@Repository
public class FloorRepositoryCustom {
	@PersistenceContext
    private EntityManager entityManager;
	public StringBuilder buildJoinClause(String floorStatus, String floorType) {
		StringBuilder joinquery = new StringBuilder();
		if(floorStatus != null) {
			joinquery.append(" left join floor_status as fs on f.floor_status_id = fs.id");
		}
		if(floorType != null) {
			joinquery.append(" left join floor_type as ft on f.floor_type_id = ft.id");
		}
		return joinquery;
	}
	
	public StringBuilder buildWhereClause(String floorStatus, String floorType, String floorName) {
		StringBuilder joinquery = new StringBuilder(" where 1=1");
		if(floorName != null) {
			joinquery.append(" and floor_name = '"+floorName+"'");
		}
		if(floorStatus != null) {
			joinquery.append(" and floor_status_name = '"+floorStatus+"'");
		}
		if(floorType != null) {
			joinquery.append(" and floor_status_name = '"+floorType+"'");
		}
		return joinquery;
	}
	
	public Set<FloorDTO> searchByField(String floorStatus, String floorType, String floorName){
		Query query = entityManager.createNativeQuery("Select * from floor as f" + buildJoinClause(floorStatus,floorType) + buildWhereClause(floorStatus, 
				floorType, floorName), FloorEntity.class);
		List<FloorEntity> list = query.getResultList();
		Set<FloorDTO> result = new HashSet<>();
		for (FloorEntity floor : list) {
			result.add(FloorConverter.FloorEntitytoDTO((FloorEntity)floor));
		}
		return result;
	}
}
