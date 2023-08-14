package com.project.ecommerc.mart247.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.ecommerc.mart247.DTO.OrderDetailsDTO;
import com.project.ecommerc.mart247.DTO.ProductDTO;
import com.project.ecommerc.mart247.entity.OrderDetailsEntity;
import com.project.ecommerc.mart247.entity.ProductEntity;
import com.project.ecommerc.mart247.repository.OrderDetailsRepository;
import com.project.ecommerc.mart247.service.OrderDetailsService;

@Service
public class OrderDetailsServiceImp implements OrderDetailsService {
	@Autowired
	private OrderDetailsRepository orderDetailsRepository;

	public Page<OrderDetailsDTO> findAllOrderDetailsByUsername(int page, int pageSize, String sortField,
			String sortDirection, String name) {
		Page<OrderDetailsDTO> dtoPage = null;
		Sort sort = Sort.by(sortField);
		sort = sortDirection.equals("desc") ? sort.descending() : sort.ascending();
		Pageable pageable = PageRequest.of(page, pageSize, sort);
		Page<OrderDetailsEntity> orderDetailsEntityPage = orderDetailsRepository.findAllOrderDetailsByUsername(name,
				pageable);
		dtoPage = convertToPageOrderDTO(orderDetailsEntityPage);
		return dtoPage;

	}

	public Page<OrderDetailsDTO> getAllOrderDetails(int page, int pageSize, String sortField, String sortDirection) {
		Page<OrderDetailsDTO> dtoPage = null;
		Sort sort = Sort.by(sortField);
		sort = sortDirection.equals("desc") ? sort.descending() : sort.ascending();
		Pageable pageable = PageRequest.of(page, pageSize, sort);
		Page<OrderDetailsEntity> orderDetailsEntityPage = orderDetailsRepository.findAllOrderDetails(pageable);
		dtoPage = convertToPageOrderDTO(orderDetailsEntityPage);
		return dtoPage;

	}

	public Page<OrderDetailsDTO> convertToPageOrderDTO(Page<OrderDetailsEntity> orderDetailsEntities) {
		Page<OrderDetailsDTO> orderDetailsDTOS = orderDetailsEntities.map(entity -> {
			OrderDetailsDTO dto = new OrderDetailsDTO(entity);
			BeanUtils.copyProperties(dto, entity);

			return dto;
		});
		return orderDetailsDTOS;
	}

	public void saveOrderDetails(OrderDetailsEntity orderDetails) {
		orderDetailsRepository.save(orderDetails);
	}

	@Override
	public void deleteOrderDetails(OrderDetailsEntity orderDetails) {
		orderDetailsRepository.delete(orderDetails);

	};

	public List<OrderDetailsDTO> findAllOrderDetailsById(long id) {
		List<OrderDetailsEntity> list = orderDetailsRepository.findAllById(id);
		return convertToListProdDTO(list);
	}
	
	private List<OrderDetailsDTO> convertToListProdDTO(List<OrderDetailsEntity> orderDetailsEntities) {
		List<OrderDetailsDTO> orderDetailsDTO = orderDetailsEntities.stream().map(entity -> {
			OrderDetailsDTO dto = new OrderDetailsDTO(entity);
			BeanUtils.copyProperties(dto, entity);
			
			return dto;
		}).collect(Collectors.toList());
		return orderDetailsDTO;
	}
}
