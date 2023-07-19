package com.RentalBuilding.JWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.RentalBuilding.DTO.MyUser;
import com.RentalBuilding.Entity.UserEntity;
import com.RentalBuilding.Repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user =  userRepository.findByUserEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("User not found in the database");
		}
		else {
			return MyUser.build(user);
		}
	}

}
