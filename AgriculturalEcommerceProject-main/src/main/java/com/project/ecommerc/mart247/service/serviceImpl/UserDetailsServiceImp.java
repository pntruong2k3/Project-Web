package com.project.ecommerc.mart247.service.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.ecommerc.mart247.DTO.MyUserDTO;
import com.project.ecommerc.mart247.entity.RoleEntity;
import com.project.ecommerc.mart247.entity.UserEntity;
import com.project.ecommerc.mart247.repository.RoleRepository;
import com.project.ecommerc.mart247.repository.UserRepository;


@Service
public class UserDetailsServiceImp implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		Long roleId = user.getRole().getId();
		Optional<RoleEntity> role = roleRepository.findById(roleId);
		MyUserDTO myUser = new MyUserDTO(user.getUsername(), user.getPassword(), user.getIsActived() == 1 ?true:false, true, true, true,
				getAuthorities(role.get()));
		myUser.setFullName(user.getName());
		myUser.setCartId(user.getCartId().getId());
		myUser.setIdAccount(user.getId());
		return myUser;
	}
	

	private Collection<? extends GrantedAuthority> getAuthorities(RoleEntity role) {
		List<GrantedAuthority> authorities = new ArrayList<>();

		authorities.add(new SimpleGrantedAuthority(role.getName()));

		return authorities;
	}
}