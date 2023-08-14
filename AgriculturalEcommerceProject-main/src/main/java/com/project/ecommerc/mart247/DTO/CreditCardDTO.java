package com.project.ecommerc.mart247.DTO;

import com.project.ecommerc.mart247.entity.CreditCardEntity;

public class CreditCardDTO {
	private Long id;

	private String name;

	private String cardNumber;

	private int expMonth;

	private int expYear;

	private int cvcCode;

	private double balance;

	private String status;

	public CreditCardDTO() {
	}
	
	public CreditCardDTO(CreditCardEntity entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.cardNumber = entity.getCardNumber();
		this.expMonth = entity.getExpMonth();
		this.expYear = entity.getExpYear();
		this.cvcCode = entity.getCvcCode();
		this.balance = entity.getBalance();
		this.status = entity.getStatus();
	}

	public CreditCardDTO(Long id, String name, String cardNumber, int expMonth, int expYear, int cvcCode,
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
