package com.RentalBuilding.Converter;

import com.RentalBuilding.DTO.UserDTO;
import com.RentalBuilding.Entity.RoleEntity;
import com.RentalBuilding.Entity.UserEntity;

public class UserConverter extends Converter {

	
	public static UserEntity DTOtoEntity(UserDTO user) {
		UserEntity obj = new UserEntity();
		obj = modelMapper.map(user, UserEntity.class);
		return obj;
	}
	
	public static UserDTO EntitytoDTO(UserEntity user) {
		UserDTO obj = modelMapper.map(user, UserDTO.class);
		for (RoleEntity item : user.getRoles()) {
			obj.getRoleCode().add(item.getRoleName());
		}
		obj.setDepartmentId(user.getDepartment().getId());
		obj.setDepartmentName(user.getDepartment().getDepartmentName());
		return obj;
	}
	
	public static void updateEntity(UserEntity user, UserDTO userDTO) {
		user.setUserAddress(userDTO.getUserAddress());
		user.setUserName(userDTO.getUserName());
		user.setUserCode(userDTO.getUserCode());
		user.setUserDateOfBirth(userDTO.getUserDateOfBirth());
		user.setUserGender(userDTO.getUserGender());
		user.setUserPhone(userDTO.getUserPhone());
		user.setUserEmail(userDTO.getUserEmail());
	}
	
}
