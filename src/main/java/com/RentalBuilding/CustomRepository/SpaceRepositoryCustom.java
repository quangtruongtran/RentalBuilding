package com.RentalBuilding.CustomRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.RentalBuilding.Converter.SpaceConverter;
import com.RentalBuilding.DTO.SpaceDTO;
import com.RentalBuilding.Entity.SpaceEntity;

@Repository
public class SpaceRepositoryCustom {
	@PersistenceContext
    private EntityManager entityManager;
	
	public StringBuilder buildJoinClause(String spaceCode, Integer spaceAreaFrom, Integer spaceAreaTo, Integer spacePriceFrom, 
			Integer spacePriceTo, String floorName, String spaceStatusName, String spaceTypeName) {
		StringBuilder joinquery = new StringBuilder();
		if(floorName != null) {
			joinquery.append(" right join floor as f on s.floor_id = f.id");
		}
		if(spaceStatusName != null) {
			joinquery.append(" right join space_status as ss on s.space_status_id = ss.id");
		}
		if(spaceTypeName != null) {
			joinquery.append(" right join space_type as st on s.space_type_id = st.id");
		}
		return joinquery;
	}
	
	public StringBuilder buildWhereClause(String spaceCode, Integer spaceAreaFrom, Integer spaceAreaTo, Integer spacePriceFrom, 
			Integer spacePriceTo, String floorName, String spaceStatusName, String spaceTypeName) {
		StringBuilder joinquery = new StringBuilder(" where 1=1");
		if(spaceCode != null) {
			joinquery.append(" and space_code = '"+spaceCode+"'");
		}
		if(spaceAreaFrom != null && spaceAreaTo == null) {
			joinquery.append(" and space_area >= '"+spaceAreaFrom+"'");
		}
		if(spaceAreaFrom == null && spaceAreaTo != null) {
			joinquery.append(" and space_area <= '"+spaceAreaTo+"'");
		}
		if(spaceAreaFrom != null && spaceAreaTo != null) {
			joinquery.append(" and space_area between '"+spaceAreaFrom+"' and '"+spaceAreaTo+"'");
		}
		if(spacePriceFrom != null && spacePriceTo == null) {
			joinquery.append(" and space_price >= '"+spacePriceFrom+"'");
		}
		if(spacePriceFrom == null && spacePriceTo != null) {
			joinquery.append(" and space_price <= '"+spacePriceTo+"'");
		}
		if(spacePriceFrom != null && spacePriceTo != null) {
			joinquery.append(" and space_price between '"+spacePriceFrom+"' and '"+spacePriceTo+"'");
		}
		if(floorName != null) {
			joinquery.append(" and floor_name = '"+floorName+"'");
		}
		if(spaceStatusName != null) {
			joinquery.append(" and space_status_name = '"+spaceStatusName+"'");
		}
		if(spaceTypeName != null) {
			joinquery.append(" and space_type_name = '"+spaceTypeName+"'");
		}
		return joinquery;
	}
	
	public Set<SpaceDTO> searchByField(String spaceCode, Integer spaceAreaFrom, Integer spaceAreaTo, Integer spacePriceFrom, 
			Integer spacePriceTo, String floorName, String spaceStatusName, String spaceTypeName){
		Query query = entityManager.createNativeQuery("Select * from space as s" + buildJoinClause(spaceCode,spaceAreaFrom,spaceAreaTo,
				spacePriceFrom, spacePriceTo,  floorName,  spaceStatusName,  spaceTypeName
				) + buildWhereClause(spaceCode,spaceAreaFrom,spaceAreaTo,
						spacePriceFrom, spacePriceTo,  floorName,  spaceStatusName,  spaceTypeName ), SpaceEntity.class);
		List<SpaceEntity> list = query.getResultList();
		Set<SpaceDTO> result = new HashSet<>();
		for (SpaceEntity space : list) {
			result.add(SpaceConverter.EntitytoDTO(space));
		}
		return result;
	}
}
