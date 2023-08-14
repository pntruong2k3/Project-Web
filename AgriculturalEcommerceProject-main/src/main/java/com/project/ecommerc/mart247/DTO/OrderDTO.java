package com.project.ecommerc.mart247.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.project.ecommerc.mart247.entity.OrderDetailsEntity;
import com.project.ecommerc.mart247.entity.OrderEntity;
import com.project.ecommerc.mart247.entity.UserEntity;

public class OrderDTO {

	private long id;
	private String fullname;
	private String email;
	private String phone;
	private String address;
	private String note;
	private LocalDateTime orderDate;
	private String status;
	private double totalMoney;
	private LocalDate deliveryDate;
	private String payMethod;
	private UserEntity user;
	 private List<OrderDetailsEntity> orderDetails;
	
	public OrderDTO() {}
	
	public OrderDTO(OrderEntity entity) {
		this.id = entity.getId();
		this.fullname = entity.getFullname();
		this.email = entity.getEmail();
		this.phone = entity.getPhone();
		this.address = entity.getAddress();
		this.note = entity.getNote();
		this.orderDate = entity.getOrderDate();
		this.status = entity.getStatus();
		this.totalMoney = entity.getTotalMoney();
		this.deliveryDate = entity.getDeliveryDate();
		this.payMethod = entity.getPayMethod();
		this.user = entity.getUser();
		this.orderDetails = entity.getOrderDetails();
	}
	
	public OrderDTO(long id, String fullname, String email, String phone, String address, String note,
			LocalDateTime orderDate, String status, double totalMoney, LocalDate deliveryDate, String payMethod, UserEntity user, List<OrderDetailsEntity> orderDetails) {
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

	@Override
	public String toString() {
		return "OrderDTO [id=" + id + ", fullname=" + fullname + ", email=" + email + ", phone=" + phone + ", address="
				+ address + ", note=" + note + ", orderDate=" + orderDate + ", status=" + status + ", totalMoney="
				+ totalMoney + ", deliveryDate=" + deliveryDate + ", payMethod=" + payMethod + ", user=" + user
				+ ", orderDetails=" + orderDetails + "]";
	}
	
	
}
