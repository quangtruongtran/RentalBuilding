package com.RentalBuilding.Converter;

import com.RentalBuilding.DTO.RoleDTO;
import com.RentalBuilding.Entity.RoleEntity;

public class RoleConverter extends Converter {
	public static RoleEntity DTOtoEntity(RoleDTO roleDTO) {
		RoleEntity obj = new RoleEntity();
		obj = modelMapper.map(roleDTO, RoleEntity.class);
		return obj;
	}
	
	public static RoleDTO EntitytoDTO(RoleEntity role) {
		RoleDTO obj = modelMapper.map(role, RoleDTO.class);
		return obj;
	}
}
