package com.RentalBuilding.Converter;

import com.RentalBuilding.DTO.DepartmentDTO;
import com.RentalBuilding.Entity.DepartmentEntity;

public class DepartmentConverter extends Converter {

		public static DepartmentEntity DTOtoEntity(DepartmentDTO departmentDTO) {
			DepartmentEntity obj = new DepartmentEntity();
			obj = modelMapper.map(departmentDTO, DepartmentEntity.class);
			return obj;
		}
		
		public static DepartmentDTO EntitytoDTO(DepartmentEntity department) {
			DepartmentDTO obj = modelMapper.map(department, DepartmentDTO.class);
			return obj;
		}
		
}
