package com.RentalBuilding.ServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.RentalBuilding.Converter.UserConverter;
import com.RentalBuilding.CustomRepository.UserRepositoryCustom;
import com.RentalBuilding.DTO.MyUser;
import com.RentalBuilding.DTO.UserDTO;
import com.RentalBuilding.Entity.ContractEntity;
import com.RentalBuilding.Entity.UserEntity;
import com.RentalBuilding.Repository.ContractRepository;
import com.RentalBuilding.Repository.DepartmentRepository;
import com.RentalBuilding.Repository.RoleRepository;
import com.RentalBuilding.Repository.UserRepository;
import com.RentalBuilding.Service.UserService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private Cloudinary cloudinary;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private ContractRepository contractRepository;
	@Autowired
	private UserRepositoryCustom userRepositoryCustom;

	@Override
	public UserEntity findByUserCode(String userCode) {
		return userRepository.findByUserCode(userCode);
	}

	@Override
	public UserDTO save(UserDTO user, MultipartFile file) {
		String imgLink = null;
		if (user.getId() == null) {
			UserEntity userSave = UserConverter.DTOtoEntity(user);
			userSave.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
			for (String roleCode : user.getRoleCode()) {
				userSave.getRoles().add(roleRepository.findByRoleCode(roleCode));
			}
			userSave.setDepartment(departmentRepository.findById(user.getDepartmentId()).get());
			try {
				Map r = this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
				imgLink = (String) r.get("secure_url");
			} catch (IOException e) {
				e.printStackTrace();
			}
			userSave.setUserImage(imgLink);
			return UserConverter.EntitytoDTO(userRepository.save(userSave));
		} else if (user.getId() != null) {
			UserEntity updateUser = userRepository.findById(user.getId()).get();
			UserConverter.updateEntity(updateUser, user);
			updateUser.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
			for (String roleCode : user.getRoleCode()) {
				updateUser.getRoles().add(roleRepository.findByRoleCode(roleCode));
			}
			updateUser.setDepartment(departmentRepository.findById(user.getDepartmentId()).get());
			if(file != null) {
				if (!file.isEmpty()) {
					try {
						Map r = this.cloudinary.uploader().upload(file.getBytes(),
								ObjectUtils.asMap("resource_type", "auto"));
						imgLink = (String) r.get("secure_url");
					} catch (IOException e) {
						e.printStackTrace();
					}
					updateUser.setUserImage(imgLink);
				}
			}
			return UserConverter.EntitytoDTO(userRepository.save(updateUser));
		}
		return null;
	}

	@Override
	public UserDTO findById(Long id) {
		Optional<UserEntity> user = userRepository.findById(id);
		if (!user.isPresent()) {
			return null;
		}
		return UserConverter.EntitytoDTO(user.get());
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public List<UserDTO> getListUsers() {
		List<UserDTO> results = new ArrayList<>();
		for (UserEntity user : userRepository.findAll()) {
			results.add(UserConverter.EntitytoDTO(user));
		}
		return results;
	}

	@Override
	public List<UserDTO> getListUsersWithPaging(Pageable pageable) {
		List<UserDTO> results = new ArrayList<>();
		for (UserEntity space : userRepository.findAll(pageable).getContent()) {
			results.add(UserConverter.EntitytoDTO(space));
		}
		return results;
	}

	@Override
	public List<ContractEntity> checkContractsOfUser(Long id) {
		return contractRepository.findByUserId(id);
	}

	public UserDetails loadUser(String username) {
		UserEntity user = userRepository.findByUserEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found in the database");
		} else {
			return MyUser.build(user);
		}
	}

	@Override
	public UserEntity findByUserEmail(String userEmail) {
		return userRepository.findByUserEmail(userEmail);
	}

	@Override
	public List<UserDTO> findAllTechStaff() {
		List<UserDTO> list = new ArrayList<>();
		for (UserEntity item : userRepository.findAllTechStaff()) {
			list.add(UserConverter.EntitytoDTO(item));
		}
		return list;
	}

	@Override
	public Set<UserDTO> searchByField(String userName, String userCode, String userEmail, String departmentCode) {
		return userRepositoryCustom.searchByField(userName, userCode, userEmail, departmentCode);
	}
}
