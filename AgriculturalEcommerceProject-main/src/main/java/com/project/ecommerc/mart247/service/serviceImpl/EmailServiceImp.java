package com.project.ecommerc.mart247.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ecommerc.mart247.service.EmailService;
import com.project.ecommerc.mart247.util.EmailSender;

@Service
public class EmailServiceImp implements EmailService {
	@Autowired
	EmailSender emailSender;

	@Override
	public void sendEmail(String EmailTo, String emailSubject, String emailContent) {

		try {
			emailSender.sendEmail(EmailTo, emailSubject, emailContent);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
