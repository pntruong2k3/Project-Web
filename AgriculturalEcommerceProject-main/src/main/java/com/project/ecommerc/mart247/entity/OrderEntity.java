package com.project.ecommerc.mart247.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.project.ecommerc.mart247.DTO.OrderDTO;

import jakarta.validation.constraints.Email;

@Entity
@Table(name = "orders")
public class OrderEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "fullname")
	private String fullname;

	@Email
	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "address")
	private String address;

	@Column(name = "note")
	private String note;

	@Column(name = "order_date", columnDefinition = "datetime")
	private LocalDateTime orderDate;

	@Column(name = "status")
	private String status;

	@Column(name = "total_money")
	private double totalMoney;

	@Column(name = "delivery_date", columnDefinition = "date")
	private LocalDate deliveryDate;

	@Column(name = "pay_method")
	private String payMethod;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderDetailsEntity> orderDetails;

	public OrderEntity() {
	}

	public OrderEntity(OrderDTO dto) {
		this.id = dto.getId();
		
		this.fullname = dto.getFullname();
		this.email = dto.getEmail();
		this.phone = dto.getPhone();
		this.address = dto.getAddress();
		this.note = dto.getNote();
		this.orderDate = dto.getOrderDate();
		this.status = dto.getStatus();
		this.totalMoney = dto.getTotalMoney();
		this.deliveryDate = dto.getDeliveryDate();
		this.payMethod = dto.getPayMethod();
		this.user = dto.getUser();
		this.orderDetails = dto.getOrderDetails();
	}

	public OrderEntity(long id, String fullname, @Email String email, String phone, String address,
			String note, LocalDateTime orderDate, String status, double totalMoney, LocalDate deliveryDate,
			String payMethod, UserEntity user, List<OrderDetailsEntity> orderDetails) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.note = note;
		this.orderDate = orderDate;
		this.status = status;
		this.totalMoney = totalMoney;
		this.deliveryDate = deliveryDate;
		this.payMethod = payMethod;
		this.user = user;
		this.orderDetails = orderDetails;
	}

	
	
	public List<OrderDetailsEntity> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetailsEntity> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

}