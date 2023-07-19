package com.RentalBuilding.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RentalBuilding.Converter.DepartmentConverter;
import com.RentalBuilding.DTO.DepartmentDTO;
import com.RentalBuilding.Entity.DepartmentEntity;
import com.RentalBuilding.Repository.DepartmentRepository;
import com.RentalBuilding.Service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public List<DepartmentDTO> getAll() {
		List<DepartmentDTO> result = new ArrayList<>();
		for (DepartmentEntity department : departmentRepository.findAll()) {
			result.add(DepartmentConverter.EntitytoDTO(department));
		}
		return result;
	}

}
