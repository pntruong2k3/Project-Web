package com.project.ecommerc.mart247.service.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.ecommerc.mart247.DTO.UserDTO;
import com.project.ecommerc.mart247.entity.CartEntity;
import com.project.ecommerc.mart247.entity.RoleEntity;
import com.project.ecommerc.mart247.entity.UserEntity;
import com.project.ecommerc.mart247.repository.RoleRepository;
import com.project.ecommerc.mart247.repository.UserRepository;
import com.project.ecommerc.mart247.service.UserService;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<UserEntity> getAllUser() {
		return null;
	}
	
	public List<RoleEntity> getAllRole(){
		return (List<RoleEntity>) roleRepository.findAll();
	}

	@Override
	public void saveUser(UserEntity user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPassword);
		
		if(user.getCartId() == null) {
			CartEntity cart = new CartEntity();
			cart.setUser(user);
			user.setCartId(cart);
			
		}
		userRepository.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteUser(id);
	}

	@Override
	public Optional<UserEntity> findUserById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public UserDTO findByUserName(String userName) {
		UserEntity user = userRepository.findByUsername(userName);
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(user, userDTO);
		return userDTO;
	}
	
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}
	
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}
	
	public UserEntity findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public Page<UserEntity> getAllUsers(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	public Page<UserDTO> getEntitiesByName(int page, int pageSize, String sortField, String sortDirection,
			String name) {
		Page<UserDTO> dtoPage = null;

		
			Sort sort = Sort.by(sortField);
			sort = sortDirection.equals("desc") ? sort.descending() : sort.ascending();
			Pageable pageable = PageRequest.of(page, pageSize, sort);
			Page<UserEntity> userEntityPage = userRepository.findByNameContainingIgnoreCase(name, pageable);
			dtoPage = convertToPageCateDTO(userEntityPage);
		
		return dtoPage;
//		return new PageImpl<>(categoryDTOList, pageable, categoryEntityPage.getTotalElements());;
	}

	public Page<UserDTO> getEntities(int page, int pageSize, String sortField, String sortDirection) {
		Page<UserDTO> dtoPage = null;
		
			Sort sort = Sort.by(sortField);
			sort = sortDirection.equals("desc") ? sort.descending() : sort.ascending();
			Pageable pageable = PageRequest.of(page, pageSize, sort);
			Page<UserEntity> userEntityPage = userRepository.getAllUsers(pageable);
			dtoPage = convertToPageCateDTO(userEntityPage);
			
		return dtoPage;
	}

	private Page<UserDTO> convertToPageCateDTO(Page<UserEntity> userEntities) {
		Page<UserDTO> userDTOS = userEntities.map(entity -> {
			UserDTO dto = new UserDTO(entity);
			BeanUtils.copyProperties(dto, entity);
			return dto;
		});
		return userDTOS;
	}

	private List<UserDTO> convertToListCateDTO(List<UserEntity> userEntities) {
		List<UserDTO> userDTOS = userEntities.stream().map(entity -> {
			UserDTO dto = new UserDTO(entity);
			BeanUtils.copyProperties(dto, entity);
			return dto;
		}).collect(Collectors.toList());
		return userDTOS;
	}
	
	public int findUsersByRole() {
		
		return userRepository.findUsersByRole().size();
	}
}
