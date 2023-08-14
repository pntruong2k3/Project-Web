package com.project.ecommerc.mart247.DTO;

import java.time.LocalDate;

import com.project.ecommerc.mart247.entity.CreditCardEntity;
import com.project.ecommerc.mart247.entity.OrderEntity;

public class PaymentDTO {
private Long id;
	
	
	private LocalDate paymentDate;
	
	
	private double amount;
	
	
	private String status;
	
	
	private OrderEntity order;
	
	
	private CreditCardEntity creditcard;
	
	public PaymentDTO() {};

	public PaymentDTO(Long id, LocalDate paymentDate, double amount, String status, OrderEntity order,
			CreditCardEntity creditcard) {
		super();
		this.id = id;
		this.paymentDate = paymentDate;
		this.amount = amount;
		this.status = status;
		this.order = order;
		this.creditcard = creditcard;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public LocalDate getPaymentDate() {
		return paymentDate;
	}


	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public OrderEntity getOrder() {
		return order;
	}


	public void setOrder(OrderEntity order) {
		this.order = order;
	}


	public CreditCardEntity getCreditcard() {
		return creditcard;
	}


	public void setCreditcard(CreditCardEntity creditcard) {
		this.creditcard = creditcard;
	}
	
	
}
