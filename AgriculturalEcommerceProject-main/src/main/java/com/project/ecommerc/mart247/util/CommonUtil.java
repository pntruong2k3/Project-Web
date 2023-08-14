package com.project.ecommerc.mart247.util;

import java.io.IOException;
import java.util.Base64;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

import com.project.ecommerc.mart247.DTO.MyUserDTO;

public class CommonUtil {

	
	public static String ConvertImg(MultipartFile file) {
		String base64Image = null;
		
		if(!file.isEmpty()) {
			byte[] fileBytes;
			try {
				fileBytes = file.getBytes();
				 base64Image = Base64.getEncoder().encodeToString(fileBytes);
			} catch (IOException e) {
			
				e.printStackTrace();
			}

			 
		}
		 return base64Image;
	}
	
	public static MyUserDTO getMyUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth instanceof AnonymousAuthenticationToken) {
			return null;
		}
		MyUserDTO myUser = (MyUserDTO) auth.getPrincipal();
		
		return myUser;
	}
}
