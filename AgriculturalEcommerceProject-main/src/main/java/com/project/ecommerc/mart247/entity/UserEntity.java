package com.project.ecommerc.mart247.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.project.ecommerc.mart247.DTO.UserDTO;

import jakarta.validation.constraints.Email;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
public class UserEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "user_name", unique = true)
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "name")
	private String name;

	@Email
	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "address")
	private String address;

	@ManyToOne
    @JoinColumn(name = "role_id") // Tên cột khóa ngoại trong bảng UserEntity
	private RoleEntity role;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private CartEntity cartId;

	@Column(columnDefinition = "datetime", name = "created_at")
	private LocalDateTime createdAt;

	@Column(columnDefinition = "datetime", name = "updated_at")
	private LocalDateTime updatedAt;

	@Column(columnDefinition = "datetime", name = "deleted_at")
	private LocalDateTime deletedAt;

	@Column(name = "is_actived")
	private Integer isActived;
	
	 @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	    private List<OrderEntity> orders;

	public UserEntity() {
	}

	public UserEntity(UserDTO userDTO) {
		super();
		this.name = userDTO.getName();
		this.email = userDTO.getEmail();
		this.username = userDTO.getUsername();
		this.password = userDTO.getPassword();
		this.phone = userDTO.getPhone();
		this.address = userDTO.getAddress();
		this.role = userDTO.getRole();
		this.createdAt = userDTO.getCreateAt();
		this.isActived = userDTO.getIsActived();
		this.orders = userDTO.getOrders();
	}

	public UserEntity(long id, String username, String password, String name, @Email String email, String phone,

			String address, RoleEntity role, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt,
			Integer isActived, List<OrderEntity> orders) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.role = role;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
		this.isActived = isActived;
		this.orders = orders;
	}

	
	
	public CartEntity getCartId() {
		return cartId;
	}

	public void setCartId(CartEntity cartId) {
		this.cartId = cartId;
	}

	public List<OrderEntity> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderEntity> orders) {
		this.orders = orders;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}

	public Integer getIsActived() {
		return isActived;
	}

	public void setIsActived(Integer isActived) {
		this.isActived = isActived;
	}

	@PrePersist
	protected void onCreate() {
		if (createdAt == null) {
			createdAt = LocalDateTime.now();
		}

	}

	@PreUpdate
	protected void onUpdate() {
		if (updatedAt == null) {
			updatedAt = LocalDateTime.now();
		}
	}

}
