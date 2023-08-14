package com.project.ecommerc.mart247.controller.client;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.ecommerc.mart247.DTO.CartDTO;
import com.project.ecommerc.mart247.DTO.CreditCardDTO;
import com.project.ecommerc.mart247.DTO.MyUserDTO;
import com.project.ecommerc.mart247.DTO.OrderDTO;
import com.project.ecommerc.mart247.DTO.OrderDetailsDTO;
import com.project.ecommerc.mart247.entity.CartEntity;
import com.project.ecommerc.mart247.entity.CartItemEntity;
import com.project.ecommerc.mart247.entity.OrderDetailsEntity;
import com.project.ecommerc.mart247.entity.OrderEntity;
import com.project.ecommerc.mart247.entity.ProductEntity;
import com.project.ecommerc.mart247.entity.UserEntity;
import com.project.ecommerc.mart247.repository.CartRepository;
import com.project.ecommerc.mart247.repository.UserRepository;
import com.project.ecommerc.mart247.service.CartItemService;
import com.project.ecommerc.mart247.service.CartService;
import com.project.ecommerc.mart247.service.CreditCardService;
import com.project.ecommerc.mart247.service.EmailService;
import com.project.ecommerc.mart247.service.OrderDetailsService;
import com.project.ecommerc.mart247.service.OrderService;
import com.project.ecommerc.mart247.service.PaymentService;
import com.project.ecommerc.mart247.util.CommonUtil;

@Controller
public class OrderHomeController {
	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderDetailsService orderDetailsService;
	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private EmailService mailService;
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private CreditCardService creditCardService;

	@Autowired
	private PaymentService paymentService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CartRepository cartRepository;

	@RequestMapping(value = "/checkOut")
	public String showOrderForm(Model model) {
		OrderDTO orderDTO = new OrderDTO();

		MyUserDTO myUser = CommonUtil.getMyUser();
		long cartId = myUser.getCartId();
		CartDTO cartDTO = cartService.getCartDTOById(cartId);

		double totalPrice = cartService.getTotalPriceDTO(cartDTO);
		int totalProduct = cartService.getTotalProductOncart();
		List<CartItemEntity> cartItems = cartDTO.getCartItem();
		for (CartItemEntity cartItemEntity : cartItems) {
			if (cartItemEntity.getQuantity() > cartItemEntity.getProduct().getQuantity()) {
				return "redirect:/cart";
			}
		}

		
		model.addAttribute("totalProduct", totalProduct);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("cartDTO", cartDTO);

		model.addAttribute("orderDTO", orderDTO);

		return "client/checkOut";
	}

	@PostMapping(value = "/checkOut")
	public String placeOrder(Model model, @ModelAttribute("orderDTO") OrderDTO orderDTO,
			@RequestParam("dateOfDelivery") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateOfDelivery) {
		HttpSession session = request.getSession();

//		OrderDTO dto = orderDTO;
		OrderEntity orderEntity = new OrderEntity(orderDTO);
		MyUserDTO myUser = CommonUtil.getMyUser();
		CartEntity cart = cartRepository.findById(myUser.getCartId()).orElseThrow();
		List<CartItemEntity> cartItems = cart.getCartItem();
		UserEntity userEntity = userRepository.findById(myUser.getIdAccount()).orElseThrow();
		orderService.saveOrderEntity(orderEntity);
		if (!cartItems.isEmpty()) {
			model.addAttribute("cartItems", cartItems);

			orderEntity.setUser(userEntity);
			orderEntity.setOrderDate(LocalDateTime.now());
			orderEntity.setDeliveryDate(dateOfDelivery);
			orderEntity.setTotalMoney(cart.getTotalPrice());
			orderEntity.setStatus("Chưa nhận hàng");

			for (CartItemEntity cartItemEntity : cartItems) {
				OrderDetailsEntity orderDetails = new OrderDetailsEntity();
				ProductEntity product = cartItemEntity.getProduct();
				int quantity = product.getQuantity() - cartItemEntity.getQuantity();
				product.setQuantity(quantity);
				orderDetails.setProduct(product);
				orderDetails.setOrder(orderEntity);
				orderDetails.setPrice(cartItemEntity.getProduct().getPrice());
				orderDetails.setQuantity(cartItemEntity.getQuantity());
				orderDetails.setTotalPrice(cartItemEntity.getTotalPrice());
				orderDetails.setProduct(cartItemEntity.getProduct());
				orderDetailsService.saveOrderDetails(orderDetails);
				cartItemService.deleteCartItem(cartItemEntity);

			}

			OrderDTO odDTO = new OrderDTO(orderEntity);
			session.setAttribute("orderDTO", odDTO);

			if (orderDTO.getPayMethod().equalsIgnoreCase("cod")) { // về trang đặt hàng thành công với COD
				// thêm dữ liệu vào shipping info
//				shippingInfoService.insert(idAccount, order.getIdOrder(), "Thanh toán khi nhận hàng",order.getSubTotal());			
				String emailContent = "Bạn đã đặt hàng thành công";
				String emailSubject = "Success";
				String EmailTo = orderEntity.getEmail();
				paymentService.insert(odDTO, null);
				mailService.sendEmail(EmailTo, emailSubject, emailContent);

				return "redirect:thankOrder";
			}
			if (orderDTO.getPayMethod().equalsIgnoreCase("card")) { // về trang payment để nhập thẻ

				return "client/performPayment";

			}

		} else { // giỏ hàng trống

			return "redirect:cart";
		}
//		
//		if (orderDTO.getPayMethod().equalsIgnoreCase("Card")) {
//
//			model.addAttribute("dateOfDelivery", dateOfDelivery);
//			model.addAttribute("paymentDTO", new PaymentDTO());
//			return "client/performPayment";
//		} else {
//			orderService.saveOrder(orderDTO, dateOfDelivery);
//
//			return "client/orderConfirmation";
//
//		}
		return "redirect:home";

	}

	@PostMapping(value = "/paymentCard")
	public String payment(Model model, @RequestParam("cardNumber") String cardNumber, @RequestParam("name") String name,
			@RequestParam("expDate") String expDate, @RequestParam("cvcCode") int cvcCode) {

		HttpSession session = request.getSession();
		OrderDTO orderDTO = (OrderDTO) session.getAttribute("orderDTO");

		try {
			String[] parts = expDate.split("/");
			int expMonth = Integer.parseInt(parts[0]);
			int expYear = Integer.parseInt(parts[1]);
			CreditCardDTO creditCardDTO = creditCardService.findbyCardNumber(cardNumber);
			double balance = creditCardDTO.getBalance();
			double totalPrice = orderDTO.getTotalMoney();
			double balanceAfter = balance - totalPrice;
			if (cardNumber.equals(creditCardDTO.getCardNumber()) && name.equals(creditCardDTO.getName())
					&& expMonth == creditCardDTO.getExpMonth() && expYear == creditCardDTO.getExpYear()
					&& cvcCode == creditCardDTO.getCvcCode()) {

				if (balanceAfter >= 0) {
					creditCardService.setbalance(cardNumber, balanceAfter);
					paymentService.insert(orderDTO, creditCardDTO);
					model.addAttribute("orderDTO", orderDTO);

					String emailContent = "Bạn đã đặt hàng thành công";
					String emailSubject = "Success";
					String EmailTo = orderDTO.getEmail();

					mailService.sendEmail(EmailTo, emailSubject, emailContent);

					return "redirect:thankOrder";
				} else {
					model.addAttribute("msg", "Số tiền trong thẻ không đủ");

					return "client/performPayment";
				}
			} else {
				model.addAttribute("msg", "Thông tin thẻ bị sai, vui lòng thử lại");
				model.addAttribute("orderDTO", orderDTO);
				return "client/performPayment";
			}

		} catch (Exception e) {
			model.addAttribute("msg", "Thông tin thẻ bị sai, vui lòng thử lại");
			model.addAttribute("orderDTO", orderDTO);
			return "client/performPayment";
		}
	}

	@GetMapping("/thankOrder")
	public String thankOrder(Model model, HttpSession session) {
		OrderDTO orderDTO = (OrderDTO) session.getAttribute("orderDTO");

		List<OrderDetailsDTO> orderDetails = orderDetailsService.findAllOrderDetailsById(orderDTO.getId());

		model.addAttribute("orderDTO", orderDTO);
		model.addAttribute("orderDetails", orderDetails);
		return "client/orderConfirmation";
	}
}
