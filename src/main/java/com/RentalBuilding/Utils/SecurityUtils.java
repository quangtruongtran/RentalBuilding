package com.RentalBuilding.Utils;

import org.springframework.security.core.context.SecurityContextHolder;

import com.RentalBuilding.DTO.MyUser;

public class SecurityUtils {
	public static MyUser getPrincipal() {
        return (MyUser) (SecurityContextHolder.getContext()).getAuthentication().getDetails();
    }
}
