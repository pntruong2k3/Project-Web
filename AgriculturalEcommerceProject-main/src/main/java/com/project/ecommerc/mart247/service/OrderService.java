package com.project.ecommerc.mart247.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import com.project.ecommerc.mart247.DTO.OrderDTO;
import com.project.ecommerc.mart247.entity.OrderEntity;

public interface OrderService {
	Page<OrderDTO> getAllOrder(int page, int pageSize, String sortField, String sortDirection);
	Page<OrderDTO> getAllOrderByUserName(int page, int pageSize, String sortField, String sortDirection, String name);
	String changeStatus(long id);
	int getTotalOrder();
//	 void saveOrder(OrderDTO dto,LocalDate deliveryDate);
	 void saveOrderEntity(OrderEntity entity);
	 
	 
}
