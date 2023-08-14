package com.project.ecommerc.mart247.service;

import com.project.ecommerc.mart247.DTO.CreditCardDTO;

public interface CreditCardService {
	CreditCardDTO findbyCardNumber(String cardNumber);
	void setbalance(String cardNumber,double balance);
}
