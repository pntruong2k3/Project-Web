package com.project.ecommerc.mart247.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ecommerc.mart247.entity.CreditCardEntity;

@Repository
public interface CreditCardRepository  extends JpaRepository<CreditCardEntity, Long>{
	CreditCardEntity findByCardNumber(String cardNumber);}
