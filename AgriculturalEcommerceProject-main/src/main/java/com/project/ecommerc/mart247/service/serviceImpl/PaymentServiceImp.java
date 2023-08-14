package com.project.ecommerc.mart247.service.serviceImpl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ecommerc.mart247.DTO.CreditCardDTO;
import com.project.ecommerc.mart247.DTO.OrderDTO;
import com.project.ecommerc.mart247.entity.CreditCardEntity;
import com.project.ecommerc.mart247.entity.OrderEntity;
import com.project.ecommerc.mart247.entity.PaymentEntity;
import com.project.ecommerc.mart247.repository.PaymentRepository;
import com.project.ecommerc.mart247.service.PaymentService;


@Service
public class PaymentServiceImp implements PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Override
	public void saveEntity(PaymentEntity entity) {
		paymentRepository.save(entity);
		
	}
	
	public void insert(OrderDTO orderDTO,CreditCardDTO creditCardDTO) {
		OrderEntity order = new OrderEntity(orderDTO);
		PaymentEntity payment = new PaymentEntity();

		if(creditCardDTO!=null) {
			CreditCardEntity creditCard  = new CreditCardEntity(creditCardDTO);
			
			payment.setCreditCard(creditCard);
			payment.setStatus("Đã thanh toán");
		}else {
			payment.setCreditCard(null);
			payment.setStatus("Chưa thanh toán");
		}
		payment.setAmount(order.getTotalMoney());
		
		payment.setOrder(order);
		payment.setPaymentDate(LocalDate.now());
		
		paymentRepository.save(payment);
	}
	
//	boolean checkPayment(String cardNumber, String name, String expMonth,
//			String expYear, String cvcCode) {
//		
//		
//	}
	
	
}
