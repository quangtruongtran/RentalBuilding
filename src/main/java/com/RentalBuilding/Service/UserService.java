package com.RentalBuilding.Service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import com.RentalBuilding.DTO.UserDTO;
import com.RentalBuilding.Entity.ContractEntity;
import com.RentalBuilding.Entity.UserEntity;

public interface UserService {
	UserEntity findByUserCode(String userCode);
	UserDTO save(UserDTO user, MultipartFile file);
	UserDTO findById(Long id);
	void deleteUser(Long id);
	List<UserDTO> getListUsers();
	List<UserDTO> getListUsersWithPaging(Pageable pageable);
	List<ContractEntity> checkContractsOfUser(Long id);
	UserDetails loadUser(String username);
	UserEntity findByUserEmail(String userEmail);
	List<UserDTO> findAllTechStaff();
	Set<UserDTO> searchByField(String userName, String userCode, String userEmail, String departmentCode);
}
