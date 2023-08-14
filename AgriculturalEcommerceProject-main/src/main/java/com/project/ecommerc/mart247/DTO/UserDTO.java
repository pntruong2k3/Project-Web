package com.project.ecommerc.mart247.DTO;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.project.ecommerc.mart247.entity.OrderEntity;
import com.project.ecommerc.mart247.entity.RoleEntity;
import com.project.ecommerc.mart247.entity.UserEntity;



public class UserDTO {
	private Long id;

	@Size(min = 3)
	@NotBlank
	private String name;

	@Email(message = "Email không hợp lệ")
	private String email;

	@NotBlank
	@Pattern(regexp = "^\\S.*$", message = "Tên người dùng không hợp lệ")
	private String username;

	@Size(min = 6, message="Mật khẩu quá ngắn")
	@NotBlank
	private String password;
	
	private String phone;
	private String address;
	private RoleEntity role ;
	private LocalDateTime createAt;
	private int isActived = 0;
    private List<OrderEntity> orders;


	public UserDTO() {};

	public UserDTO(Long id, String name, String email, String username, String password, String phone, String address,
			RoleEntity role,LocalDateTime createAt, int isActived, List<OrderEntity> orders) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.role = role;
		this.createAt = createAt;
		this.isActived = isActived;
		this.orders = orders;
	}

	
	
	public List<OrderEntity> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderEntity> orders) {
		this.orders = orders;
	}
	
	public UserDTO(UserEntity userEntity) {
		this.id = userEntity.getId();
		this.name = userEntity.getName();
		this.email = userEntity.getEmail();
		this.username = userEntity.getUsername();
		this.password = userEntity.getPassword();
		this.phone = userEntity.getPhone();
		this.address = userEntity.getAddress();
		this.role = userEntity.getRole();
		this.createAt = userEntity.getCreatedAt();
		this.isActived = userEntity.getIsActived();
		this.orders = userEntity.getOrders();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public int getIsActived() {
		return isActived;
	}

	public void setIsActived(int isActived) {
		this.isActived = isActived;
	}
	
	
}
