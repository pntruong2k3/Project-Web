package com.project.ecommerc.mart247.service.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.ecommerc.mart247.DTO.MyUserDTO;
import com.project.ecommerc.mart247.DTO.OrderDTO;
import com.project.ecommerc.mart247.entity.CartEntity;
import com.project.ecommerc.mart247.entity.CartItemEntity;
import com.project.ecommerc.mart247.entity.CreditCardEntity;
import com.project.ecommerc.mart247.entity.OrderDetailsEntity;
import com.project.ecommerc.mart247.entity.OrderEntity;
import com.project.ecommerc.mart247.entity.PaymentEntity;
import com.project.ecommerc.mart247.entity.UserEntity;
import com.project.ecommerc.mart247.repository.CartRepository;
import com.project.ecommerc.mart247.repository.CreditCardRepository;
import com.project.ecommerc.mart247.repository.OrderRepository;
import com.project.ecommerc.mart247.repository.UserRepository;
import com.project.ecommerc.mart247.service.CartItemService;
import com.project.ecommerc.mart247.service.EmailService;
import com.project.ecommerc.mart247.service.OrderDetailsService;
import com.project.ecommerc.mart247.service.OrderService;
import com.project.ecommerc.mart247.service.PaymentService;
import com.project.ecommerc.mart247.util.CommonUtil;

@Service
public class OrderServiceImp implements OrderService {
	@Autowired
	private EmailService mailService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CartRepository cartRepositoty;
	@Autowired
	private CreditCardRepository creditCardRepository;
	@Autowired
	private OrderDetailsService orderDetailsService;
	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private PaymentService paymentService;

	public void saveOrderEntity(OrderEntity entity) {
		orderRepository.save(entity);
	}

	@Override
	public Page<OrderDTO> getAllOrderByUserName(int page, int pageSize, String sortField, String sortDirection,
			String name) {
		Page<OrderDTO> dtoPage = null;
		Sort sort = Sort.by(sortField);
		sort = sortDirection.equals("desc") ? sort.descending() : sort.ascending();
		Pageable pageable = PageRequest.of(page, pageSize, sort);
		Page<OrderEntity> orderEntityPage = orderRepository.findByOderByUser_Name(name, pageable);
		dtoPage = convertToPageOrderDTO(orderEntityPage);
		return dtoPage;
	}

	@Override
	public Page<OrderDTO> getAllOrder(int page, int pageSize, String sortField, String sortDirection) {
		Page<OrderDTO> dtoPage = null;
		Sort sort = Sort.by(sortField);
		sort = sortDirection.equals("desc") ? sort.descending() : sort.ascending();
		Pageable pageable = PageRequest.of(page, pageSize, sort);
		Page<OrderEntity> orderEntityPage = orderRepository.findAllOrder(pageable);
		dtoPage = convertToPageOrderDTO(orderEntityPage);
		return dtoPage;
	}

	private Page<OrderDTO> convertToPageOrderDTO(Page<OrderEntity> orderEntities) {
		Page<OrderDTO> orderDTOS = orderEntities.map(entity -> {
			OrderDTO dto = new OrderDTO(entity);
			BeanUtils.copyProperties(dto, entity);

			return dto;
		});
		return orderDTOS;
	}

	@Override
	public String changeStatus(long id) {
		Optional<OrderEntity> entityOptional = orderRepository.findById(id);
		OrderEntity entity = entityOptional.get();
		if (entity.getStatus().equalsIgnoreCase("Chưa nhận hàng")) {
			entity.setStatus("Đã nhận hàng");
		} else {
			entity.setStatus("chưa nhận hàng");
		}
		orderRepository.save(entity);

		return entity.getStatus();

	}

	@Override
	public int getTotalOrder() {
		int totalOrder = orderRepository.findAllOrder().size();
		return totalOrder;
	}

	public List<OrderDTO> find5TopOrder(int page, int pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);
		List<OrderDTO> dtoList = convertToOrderDTOList(orderRepository.find5TopOrder(pageable));
		return dtoList;
	}

	public List<OrderDTO> convertToOrderDTOList(List<OrderEntity> orderEntityList) {
		return orderEntityList.stream().map(orderEntity -> {
			OrderDTO orderDTO = new OrderDTO();
			BeanUtils.copyProperties(orderEntity, orderDTO);
			return orderDTO;
		}).collect(Collectors.toList());
	}

//	public void saveOrder(OrderDTO orderDTO, LocalDate deliveryDate, String cardNumber, String name, int expMonth,
//			int expYear, int cvcCode, CreditCardEntity creditCard, String status) {
//		MyUserDTO myUser = CommonUtil.getMyUser();
//		CartEntity cart = cartRepositoty.findById(myUser.getCartId()).orElseThrow();
//		OrderEntity order = orderRepository.findById(orderDTO.getId()).orElseThrow();
//
//		PaymentEntity payment = new PaymentEntity();
//		payment.setAmount(cart.getTotalPrice());
//		if (payment.getAmount() < creditCard.getBalance()) {
//			double newBalance = creditCard.getBalance() - payment.getAmount();
//			creditCard.setBalance(newBalance);
//			paymentService.saveEntity(payment);
//
//		}
//
//		CreditCardEntity creditCardEntity = creditCardRepository.findByCardNumber(cardNumber);
//		if (cardNumber.equals(creditCardEntity.getCardNumber()) && name.equals(creditCardEntity.getName())
//				&& expMonth == creditCardEntity.getExpMonth() && expYear == creditCardEntity.getExpYear()
//				&& cvcCode == creditCardEntity.getCvcCode()) {
//
//		}
//
//		payment.setCreditcard(creditCard);
//		payment.setPaymentDate(LocalDate.now());
//		payment.setStatus(status);
//		payment.setOrder(order);
//
//		OrderEntity orderEntity = new OrderEntity(orderDTO);
//		UserEntity userEntity = userRepository.findById(myUser.getIdAccount()).orElseThrow();
//		List<CartItemEntity> ListCartItem = cart.getCartItem();
//		double totalMoney = ListCartItem.stream().mapToDouble(CartItemEntity::getTotalPrice).sum();
//
//		orderEntity.setOrderDate(LocalDateTime.now());
//		orderEntity.setDeliveryDate(deliveryDate);
//		orderEntity.setTotalMoney(totalMoney);
//		orderEntity.setUser(userEntity);
//		orderEntity.setStatus("Đang vận chuyển");
//
//		String emailContent = "Bạn đã đặt hàng thành công";
//		String emailSubject = "Success";
//		String EmailTo = orderEntity.getEmail();
//
//		mailService.sendEmail(EmailTo, emailSubject, emailContent);
//
//		orderRepository.save(orderEntity);
//		for (CartItemEntity cartItemEntity : ListCartItem) {
//			OrderDetailsEntity orderDetails = new OrderDetailsEntity();
//			orderDetails.setOrder(orderEntity);
//			orderDetails.setPrice(cartItemEntity.getProduct().getPrice());
//			orderDetails.setQuantity(cartItemEntity.getQuantity());
//			orderDetails.setTotalPrice(cartItemEntity.getTotalPrice());
//			orderDetails.setProduct(cartItemEntity.getProduct());
//			orderDetailsService.saveOrderDetails(orderDetails);
//			cartItemService.deleteCartItem(cartItemEntity);
//
//		}
//	}

}
