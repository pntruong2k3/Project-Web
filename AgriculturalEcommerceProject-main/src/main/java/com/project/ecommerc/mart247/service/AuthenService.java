package com.project.ecommerc.mart247.service;

public interface AuthenService {

	void changeStatus(Long id);
	void changePassword(Long id, String newPassword);

}
