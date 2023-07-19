package com.RentalBuilding.Converter;

import com.RentalBuilding.DTO.SpaceDTO;
import com.RentalBuilding.Entity.SpaceEntity;

public class SpaceConverter extends Converter {
	public static SpaceEntity DTOtoEntity(SpaceDTO space) {
		SpaceEntity obj = new SpaceEntity();
		obj = modelMapper.map(space, SpaceEntity.class);
		return obj;
	}
	
	public static SpaceDTO EntitytoDTO(SpaceEntity space) {
		SpaceDTO obj = modelMapper.map(space, SpaceDTO.class);
		obj.setFloorName(space.getFloor().getFloorName());
		obj.setSpaceStatusName(space.getSpaceStatus().getSpaceStatusName());
		obj.setSpaceTypeName(space.getSpaceType().getSpaceTypeName());
		if(space.getContract() != null) {
			obj.setContractCode(space.getContract().getContractCode());
		} else {
			obj.setContractCode("");
		}
		return obj;
	}

	public static void updateEntity(SpaceEntity space, SpaceDTO spaceDTO) {
		space.setSpaceCode(spaceDTO.getSpaceCode());
		space.setSpaceArea(spaceDTO.getSpaceArea());
		space.setSpacePrice(spaceDTO.getSpacePrice());
		space.setSpaceServiceFee(spaceDTO.getSpaceServiceFee());
		space.setSpaceDescription(spaceDTO.getSpaceDescription());
	}
}
