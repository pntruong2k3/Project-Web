package com.project.ecommerc.mart247.controller.admin;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.ecommerc.mart247.DTO.UserDTO;
import com.project.ecommerc.mart247.entity.RoleEntity;
import com.project.ecommerc.mart247.entity.UserEntity;
import com.project.ecommerc.mart247.repository.RoleRepository;
import com.project.ecommerc.mart247.service.serviceImpl.UserServiceImp;
import com.project.ecommerc.mart247.util.Constant;

@Controller
@RequestMapping("/admin")
public class UserAdminController {
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserServiceImp userServiceImp;

	@RequestMapping(value = "/user")
	public String home(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int pageSize, @RequestParam(defaultValue = "id") String sortField,
			@RequestParam(defaultValue = "asc") String sortDirection, @RequestParam(required = false) String name) {

//		List<UserEntity> users = userService.getAllUser();
		Page<UserDTO> userPage;
		if (name != null && !name.isEmpty()) {
			userPage = userServiceImp.getEntitiesByName(page, pageSize, sortField, sortDirection, name);
		} else {
			userPage = userServiceImp.getEntities(page, pageSize, sortField, sortDirection);
		}

		UserDTO userDTO = new UserDTO();
		List<RoleEntity> roles = roleRepository.findAll();
		
		model.addAttribute("roles", roles);
		model.addAttribute("sortField", sortField);
		model.addAttribute("name", name);
		model.addAttribute("sortDirection", sortDirection);

//		model.addAttribute("users", users);
		model.addAttribute("userDTO", userDTO);
		model.addAttribute("userPage", userPage);
		return "admin/manageUser";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveAdminUser(Model model, @Valid @ModelAttribute UserDTO userDTO, BindingResult result,
			@RequestParam("password") String password, @RequestParam("password2") String password2) {
		try {
			if (result.hasErrors()) {
				model.addAttribute("userDTO", userDTO);
				return "redirect:/admin/user";	
			}
			boolean existsUsername = userServiceImp.existsByUsername(userDTO.getUsername());
			boolean existsEmail = userServiceImp.existsByEmail(userDTO.getEmail());

			if (existsUsername) {
				model.addAttribute(Constant.KEY_ATTRIBUTE.MESSENGER, "Username đã được sử dụng");
				model.addAttribute("userDTO", userDTO);
				return "redirect:/admin/user";	
			} else if (existsEmail) {
				model.addAttribute(Constant.KEY_ATTRIBUTE.MESSENGER, "Email đã được sử dụng");
				model.addAttribute("userDTO", userDTO);
				return "redirect:/admin/user";	
			}

		} catch (Exception e) {

			model.addAttribute(Constant.KEY_ATTRIBUTE.MESSENGER, "Hệ thống bị lỗi");
			model.addAttribute("userDTO", userDTO);
			return "redirect:/admin/user";	
		}
		try {
			if (!password.equals(password2)) {
				model.addAttribute(Constant.KEY_ATTRIBUTE.MESSENGER, "Mật khẩu nhập lại không chính xác");
				return "redirect:/admin/user";	
			}

		} catch (Exception e) {
			model.addAttribute(Constant.KEY_ATTRIBUTE.MESSENGER, Constant.ERROR.COMMON_ERROR);
		}
		model.addAttribute(Constant.KEY_ATTRIBUTE.MESSENGER, Constant.SUCCESS.RESET_PASS_SUCCESS);
		/* return "redirect:/login"; */

		
		try {
			if (userDTO.getRole() == null) {
				RoleEntity role = new RoleEntity();
				role.setId(3);
				userDTO.setRole(role);
			}
			UserEntity userEntity = new UserEntity(userDTO);

			userServiceImp.saveUser(userEntity);
		} catch (Exception e) {
			return "redirect:/admin/user";		}
		return "redirect:/admin/user";	
		
	}
	
	@RequestMapping(value = "/user/edit/{id}")
	public String editUser(Model model, @PathVariable("id") long id) {
		Optional<UserEntity> userById = userServiceImp.findUserById(id);
		List<RoleEntity> roles = roleRepository.findAll();

		model.addAttribute("roles", roles);
		model.addAttribute("userById", userById);
		return "admin/editUser";
	}

	@RequestMapping(value = "/user/edit", method = RequestMethod.POST)
	public String saveEditUser(Model model, @ModelAttribute UserDTO userDTO,
			@RequestParam("Role") UserEntity selectedRole) {
		Optional<UserEntity> userOptional = userServiceImp.findUserById(userDTO.getId());
		UserEntity user = userOptional.get();

		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPhone(userDTO.getPhone());
		user.setAddress(userDTO.getAddress());
		user.setRole(userDTO.getRole());

		userServiceImp.saveUser(user);

		return "redirect:/admin/user";
	}

	@RequestMapping(value = "/user/delete/{id}")
	@Transactional
	public String deleteUser(Model model, @PathVariable("id") long id) {
		userServiceImp.deleteUser(id);
		return "redirect:/admin/user";
	}
}
