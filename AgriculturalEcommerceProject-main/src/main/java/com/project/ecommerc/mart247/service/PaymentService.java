package com.project.ecommerc.mart247.service;

import com.project.ecommerc.mart247.DTO.CreditCardDTO;
import com.project.ecommerc.mart247.DTO.OrderDTO;
import com.project.ecommerc.mart247.entity.PaymentEntity;

public interface PaymentService {
	void saveEntity(PaymentEntity entity);
	void insert(OrderDTO orderDTO,CreditCardDTO creditCardDTO);
}
