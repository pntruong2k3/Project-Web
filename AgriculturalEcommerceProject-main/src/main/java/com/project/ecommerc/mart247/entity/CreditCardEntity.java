package com.project.ecommerc.mart247.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.project.ecommerc.mart247.DTO.CreditCardDTO;

@Entity
@Table(name="Credit_Card")
public class CreditCardEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "card_number")
	private String cardNumber;
	
	@Column(name = "exp_month")
	private int expMonth;
	
	@Column(name = "exp_year")
	private int expYear;
	
	@Column(name = "cvc_code")
	private int cvcCode;
	
	@Column(name = "balance")
	private double balance;
	
	@Column(name = "status")
	private String status;
	
	public CreditCardEntity() {}
	

	public CreditCardEntity(CreditCardDTO dto) {
		this.id = dto.getId();
		this.name = dto.getName();
		this.cardNumber = dto.getCardNumber();
		this.expMonth = dto.getExpMonth();
		this.expYear = dto.getExpYear();
		this.cvcCode = dto.getCvcCode();
		this.balance = dto.getBalance();
		this.status = dto.getStatus();
	}

	public CreditCardEntity(Long id, String name, String cardNumber, int expMonth, int expYear, int cvcCode,
			double balance, String status) {
		super();
		this.id = id;
		this.name = name;
		this.cardNumber = cardNumber;
		this.expMonth = expMonth;
		this.expYear = expYear;
		this.cvcCode = cvcCode;
		this.balance = balance;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getExpMonth() {
		return expMonth;
	}

	public void setExpMonth(int expMonth) {
		this.expMonth = expMonth;
	}

	public int getExpYear() {
		return expYear;
	}

	public void setExpYear(int expYear) {
		this.expYear = expYear;
	}

	public int getCvcCode() {
		return cvcCode;
	}

	public void setCvcCode(int cvcCode) {
		this.cvcCode = cvcCode;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	};
	
	
	
	
}
