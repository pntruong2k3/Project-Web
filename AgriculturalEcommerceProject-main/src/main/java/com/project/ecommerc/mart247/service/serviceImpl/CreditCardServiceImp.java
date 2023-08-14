package com.project.ecommerc.mart247.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ecommerc.mart247.DTO.CreditCardDTO;
import com.project.ecommerc.mart247.entity.CreditCardEntity;
import com.project.ecommerc.mart247.repository.CreditCardRepository;
import com.project.ecommerc.mart247.service.CreditCardService;

@Service
public class CreditCardServiceImp implements CreditCardService{
	
	@Autowired
	private CreditCardRepository creditCardRepository;
	
	public CreditCardDTO findbyCardNumber(String cardNumber) {
		
		CreditCardEntity entity = creditCardRepository.findByCardNumber(cardNumber);
		CreditCardDTO dto = new CreditCardDTO(entity);
		return dto;
		
	}
	
	public void setbalance(String cardNumber,double balance) {
		CreditCardEntity creditCard = creditCardRepository.findByCardNumber(cardNumber);
		creditCard.setBalance(balance);
		
		creditCardRepository.save(creditCard);
	}
}
