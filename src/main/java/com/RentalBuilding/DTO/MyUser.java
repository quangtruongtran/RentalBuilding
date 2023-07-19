package com.RentalBuilding.DTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.RentalBuilding.Entity.RoleEntity;
import com.RentalBuilding.Entity.UserEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyUser implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	private String userEmail;
	private String nameOfUser;
	private String userCode;
	private String userPassword;
	private Collection<? extends GrantedAuthority> roles;
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return roles;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return userPassword;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userEmail;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	  public static MyUser build(UserEntity userentity) {
	        List<GrantedAuthority> authorities = new ArrayList<>();
	        for (RoleEntity role : userentity.getRoles()) {
				authorities.add(new SimpleGrantedAuthority(role.getRoleCode()));
			}
	        return new MyUser(
	        		userentity.getId(),
	        		userentity.getUserEmail(),
	        		userentity.getUserName(),
	        		userentity.getUserCode(),
	        		userentity.getUserPassword(),
	                authorities
	        );
	    }
	public MyUser(Long id, String userEmail, String nameOfUser, String userCode, String userPassword,
			Collection<? extends GrantedAuthority> roles) {
		super();
		this.id = id;
		this.userEmail = userEmail;
		this.nameOfUser = nameOfUser;
		this.userCode = userCode;
		this.userPassword = userPassword;
		this.roles = roles;
	}
	

}
