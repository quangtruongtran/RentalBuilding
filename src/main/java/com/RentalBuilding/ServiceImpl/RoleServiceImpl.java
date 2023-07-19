package com.RentalBuilding.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RentalBuilding.Converter.RoleConverter;
import com.RentalBuilding.DTO.RoleDTO;
import com.RentalBuilding.Entity.RoleEntity;
import com.RentalBuilding.Repository.RoleRepository;
import com.RentalBuilding.Service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<RoleDTO> getAll() {
		List<RoleDTO> result = new ArrayList<>();
		for (RoleEntity role : roleRepository.findAll()) {
			result.add(RoleConverter.EntitytoDTO(role));
		}
		return result;
	}

}
