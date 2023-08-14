package com.project.ecommerc.mart247.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.project.ecommerc.mart247.DTO.PaymentDTO;

@Entity
@Table(name="Payment")
public class PaymentEntity {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="payment_date")
	private LocalDate paymentDate;
	
	@Column(name="amount")
	private double amount;
	
	@Column(name="status")
	private String status;
	
	@JoinColumn(name="id_order")
	@ManyToOne
	private OrderEntity order;
	
	@JoinColumn(name="id_creditCard")
	@ManyToOne
	private CreditCardEntity creditCard;
	
	public PaymentEntity() {}
	
	public PaymentEntity(PaymentDTO dto) {
		
		this.id = dto.getId();
		this.paymentDate = dto.getPaymentDate();
		this.amount = dto.getAmount();
		this.status = dto.getStatus();
		this.order = dto.getOrder();
		this.creditCard = dto.getCreditcard();
	}
	

	public PaymentEntity(Long id, LocalDate paymentDate, double amount, String status, OrderEntity order,
			CreditCardEntity creditCard) {
		super();
		this.id = id;
		this.paymentDate = paymentDate;
		this.amount = amount;
		this.status = status;
		this.order = order;
		this.creditCard = creditCard;
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

	public CreditCardEntity getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCardEntity creditCard) {
		this.creditCard = creditCard;
	};
	
	
	
}
