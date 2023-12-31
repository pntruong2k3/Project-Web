package com.project.ecommerc.mart247.util;

import com.project.ecommerc.mart247.DTO.MyUserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

public class SecurityUtils {
	// Lưu thông tin và duyb trì data của dối tượng đang đăng nhập để có thể get và sử dụng ở tầng views 
	public static MyUserDTO getPrincipal() {
		Object object = (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
		MyUserDTO myUser = (MyUserDTO) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
        return myUser;
    }
	
	
	// Get code đã put vào Authorities trong class CustomUserDetailsService
	@SuppressWarnings("unchecked")
	public static List<String> getAuthorities(){
	List<String> results = new ArrayList<>();
	
	// Get authorities đã được put ở class CustomUserDetailsService
	List<GrantedAuthority> authorities = (List<GrantedAuthority>)(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
	
	// Chạy vòng lặp và add code (admin or user) authorites vào results để đưa vào bước Anthortization
    for (GrantedAuthority authority : authorities) {
        results.add(authority.getAuthority());
    }
	return results;
}
}
