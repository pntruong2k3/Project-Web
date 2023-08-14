package com.project.ecommerc.mart247.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.project.ecommerc.mart247.DTO.OrderDetailsDTO;
import com.project.ecommerc.mart247.entity.OrderDetailsEntity;

public interface OrderDetailsService {
	Page<OrderDetailsDTO> getAllOrderDetails(int page, int pageSize, String sortField, String sortDirection);

	Page<OrderDetailsDTO> findAllOrderDetailsByUsername(int page, int pageSize, String sortField, String sortDirection,
			String name);
	
	Page<OrderDetailsDTO> convertToPageOrderDTO(Page<OrderDetailsEntity> orderDetailsEntities);
	
	void saveOrderDetails(OrderDetailsEntity orderDetails);
	void deleteOrderDetails(OrderDetailsEntity orderDetails);
	 List<OrderDetailsDTO> findAllOrderDetailsById(long id);

}
