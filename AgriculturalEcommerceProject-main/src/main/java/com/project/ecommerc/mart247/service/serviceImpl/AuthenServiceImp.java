package com.project.ecommerc.mart247.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ecommerc.mart247.entity.UserEntity;
import com.project.ecommerc.mart247.repository.UserRepository;
import com.project.ecommerc.mart247.service.AuthenService;

@Service
public class AuthenServiceImp implements AuthenService{
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserServiceImp userServiceImp;
	
	@Override
	public void changeStatus(Long id) {
		boolean exists = userRepository.existsById(id);
		if(exists) {
			UserEntity user = userRepository.getOne(id);
			user.setIsActived(1);		
			userRepository.save(user);
		}
	}
	@Override
	public void changePassword(Long id, String newPassword) {
		boolean exists = userRepository.existsById(id);
		if(exists) {
			UserEntity user = userRepository.getOne(id);
			user.setPassword(newPassword);;		
			userServiceImp.saveUser(user);
		}
	}
}
