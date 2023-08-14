package com.project.ecommerc.mart247.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.ecommerc.mart247.DTO.UserDTO;
import com.project.ecommerc.mart247.config.Properties;
import com.project.ecommerc.mart247.entity.RoleEntity;
import com.project.ecommerc.mart247.entity.UserEntity;
import com.project.ecommerc.mart247.repository.RoleRepository;
import com.project.ecommerc.mart247.service.serviceImpl.AuthenServiceImp;
import com.project.ecommerc.mart247.service.serviceImpl.EmailServiceImp;
import com.project.ecommerc.mart247.service.serviceImpl.UserServiceImp;
import com.project.ecommerc.mart247.util.Constant;
import com.project.ecommerc.mart247.util.SecurityUtils;

@Controller
public class UserController {

	@Autowired
	private EmailServiceImp mailService;

	@Autowired
	private UserServiceImp userServiceImp;
	@Autowired
	private AuthenServiceImp authenServiceImp;

	@Value("${server.port}")
	private String port;
	@Value("${server.address}")
	private String address;
	@Autowired
	private Properties properties;
	@Autowired
	private RoleRepository roleRepository;

	/*
	 * @RequestMapping(value = "/login") public String login(Model model) { return
	 * "login"; }
	 */

	@RequestMapping(value = "/add")
	public String addUser(Model model) {

		List<RoleEntity> roles = userServiceImp.getAllRole();

		model.addAttribute("roles", roles);
		model.addAttribute("userDTO", new UserDTO());

		return "authen/addUser";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String save(Model model, @Valid @ModelAttribute UserDTO userDTO, BindingResult result,
			@RequestParam("password") String password, @RequestParam("password2") String password2) {
		try {
			if (result.hasErrors()) {
				model.addAttribute("userDTO", userDTO);
				return "authen/addUser";
			}
			boolean existsUsername = userServiceImp.existsByUsername(userDTO.getUsername());
			boolean existsEmail = userServiceImp.existsByEmail(userDTO.getEmail());

			if (existsUsername) {
				model.addAttribute(Constant.KEY_ATTRIBUTE.MESSENGER, "Username đã được sử dụng");
				model.addAttribute("userDTO", userDTO);
				return "authen/addUser";
			} else if (existsEmail) {
				model.addAttribute(Constant.KEY_ATTRIBUTE.MESSENGER, "Email đã được sử dụng");
				model.addAttribute("userDTO", userDTO);
				return "authen/addUser";
			}

		} catch (Exception e) {	

			model.addAttribute(Constant.KEY_ATTRIBUTE.MESSENGER, "Hệ thống bị lỗi");
			model.addAttribute("userDTO", userDTO);
			return "authen/addUser";
		}
		try {
			if (!password.equals(password2)) {
				model.addAttribute(Constant.KEY_ATTRIBUTE.MESSENGER, "Mật khẩu nhập lại không chính xác");
				return "authen/addUser";
			}

		} catch (Exception e) {
			model.addAttribute(Constant.KEY_ATTRIBUTE.MESSENGER, Constant.ERROR.COMMON_ERROR);
		}
		model.addAttribute(Constant.KEY_ATTRIBUTE.MESSENGER, Constant.SUCCESS.RESET_PASS_SUCCESS);
		/* return "redirect:/login"; */

		/* try { */
		if (userDTO.getRole() == null) {
			RoleEntity role = new RoleEntity();
			role.setId(3);
			userDTO.setRole(role);
		}
		UserEntity userEntity = new UserEntity(userDTO);

		userServiceImp.saveUser(userEntity);

//			 String emailContent = Constant.SENDMAIL.ADD_USER + "<a href='"+ properties.getURL() + Constant.ENPOINT.ADD_USER + user.getId()+ "'>Xác nhận tài khoản</a>";
		String emailContent = Constant.SENDMAIL.ADD_USER + " http://localhost:8080/register/" + userEntity.getId();
		String emailSubject = "Kích hoạt tài khoản";
		String EmailTo = userDTO.getEmail();
		mailService.sendEmail(EmailTo, emailSubject, emailContent);

		/* } catch (Exception e) { */
		model.addAttribute(Constant.KEY_ATTRIBUTE.MESSENGER, "Email xác nhận tài khoản đã được gửi");

		return "login";
		/* } */
	}

	

	@RequestMapping(value = "/register/{id}")
	public String register(Model model, @PathVariable("id") Long id) {
		try {
			authenServiceImp.changeStatus(id);
		} catch (Exception e) {
			model.addAttribute(Constant.KEY_ATTRIBUTE.MESSENGER, Constant.ERROR.COMMON_ERROR);
			return "authen/register";
		}
		model.addAttribute(Constant.KEY_ATTRIBUTE.MESSENGER, Constant.SUCCESS.REGISTER_SUCCESS);
		return "authen/register";
	}

	@RequestMapping(value = "/forgotPassword/form")
	public String forgotPasswordForm(Model model) {
		return "authen/forgotPassword";
	}

	@RequestMapping(value = "/forgotPassword")
	public String forgotPassword(Model model, @RequestParam("email") String email) {
		/* try { */
		UserEntity user = userServiceImp.findByEmail(email);
		if (user != null) {
			String emailContent = "Cài đặt lại mật khẩu của bạn hãy bấm vào "
					+ "http://localhost:8080/forgotPassword/checkEmail/" + user.getId();
			String emailSubject = "Thiết lập lại mật khẩu";
			String EmailTo = email;
			mailService.sendEmail(EmailTo, emailSubject, emailContent);
		}
		/* } catch (Exception e) { */
		
		model.addAttribute(Constant.KEY_ATTRIBUTE.MESSENGER, "Email thiết lập lại mật khẩu đã được gửi đi");
//			return "login";
		/* } */

		return "login";
	}

	@RequestMapping(value = "/forgotPassword/checkEmail/{id}")
	public String checkEmail(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("userId", id);
		} catch (Exception e) {
			model.addAttribute(Constant.KEY_ATTRIBUTE.MESSENGER, Constant.ERROR.COMMON_ERROR);
			return "authen/forgotPasswordForm";
		}

		return "authen/forgotPasswordForm";
	}

	@RequestMapping(value = "/forgotPassword/resetPassword/")
	public String resetPassword(Model model, @RequestParam("password") String password,
			@RequestParam("password2") String password2, @RequestParam("userId") Long id) {
		try {
			if (!password.equals(password2)) {
				model.addAttribute(Constant.KEY_ATTRIBUTE.MESSENGER, "Mật khẩu nhập lại không chính xác");
				return "authen/forgotPasswordForm";
			}
			authenServiceImp.changePassword(id, password2);

		} catch (Exception e) {
			model.addAttribute(Constant.KEY_ATTRIBUTE.MESSENGER, Constant.ERROR.COMMON_ERROR);
			return "login";
		}
		model.addAttribute(Constant.KEY_ATTRIBUTE.MESSENGER, Constant.SUCCESS.RESET_PASS_SUCCESS);
		return "login";
	}

//	@GetMapping("/")
//    public String validateLoginInfo(Model model, @Valid  loginForm, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "login";
//        }
//        model.addAttribute("user", loginForm.getuserName());
//        return "home";
//    }

	@GetMapping("/login")
	public String Login(Model model) {
		
		try {
			UserDTO user = userServiceImp.findByUserName(SecurityUtils.getPrincipal().getUsername());
			
		} catch (Exception e) {
			
			
				
			return "login";
		}

		return "redirect:home";
	}

	@RequestMapping(value = "/thoat", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ModelAndView("redirect:/trang-chu");
	}

	/*
	 * @RequestMapping(value = "/edit", method = RequestMethod.GET) public String
	 * editUser(@RequestParam("id") Long userId, Model model) { Optional<User>
	 * userEdit = userService.findUserById(userId); userEdit.ifPresent(user ->
	 * model.addAttribute("user", user)); return "editUser"; }
	 *
	 * 
	 */
	/*
	 * @ResponseStatus(HttpStatus.BAD_REQUEST)
	 * 
	 * @ExceptionHandler(Exception.class) public Map<String, String>
	 * handleValidationExceptions( MethodArgumentNotValidException ex) { Map<String,
	 * String> errors = new HashMap<>();
	 * ex.getBindingResult().getAllErrors().forEach((error) -> { String fieldName =
	 * ((FieldError) error).getField(); String errorMessage =
	 * error.getDefaultMessage(); errors.put(fieldName, errorMessage); }); return
	 * errors; }
	 */
}
