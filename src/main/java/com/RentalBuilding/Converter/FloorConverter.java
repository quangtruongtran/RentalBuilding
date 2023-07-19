package com.RentalBuilding.Converter;

import com.RentalBuilding.DTO.FloorDTO;
import com.RentalBuilding.Entity.FloorEntity;

public class FloorConverter extends Converter {

	public static FloorEntity FloorDTOtoEntity(FloorDTO floor) {
		FloorEntity obj = new FloorEntity();
		obj = modelMapper.map(floor, FloorEntity.class);
		return obj;
	}
	
	public static FloorDTO FloorEntitytoDTO(FloorEntity floor) {
		FloorDTO obj = modelMapper.map(floor, FloorDTO.class);
		obj.setFloorStatusName(floor.getFloorStatus().getFloorStatusName());
		obj.setFloorTypeName(floor.getFloorType().getFloorTypeName());
		return obj;
	}
}
