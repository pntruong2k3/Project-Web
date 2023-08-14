package com.project.ecommerc.mart247.security;

import com.project.ecommerc.mart247.util.SecurityUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// Nơi xử lý Authoziration ( phân quyền admin và user sau khi authentication thành công)

public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		String targetUrl = determineTargetUrl(authentication);// Hàm định tuyến URL sẽ được trả về
		if (response.isCommitted()) {
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	// Hàm định tuyến URL sẽ được trả về
	private String determineTargetUrl(Authentication authentication) {
			String url="";
			
			List<String> roles = SecurityUtils.getAuthorities();
			if(isAdmin(roles)) {
				url = "/admin/home";
			}
			else if(isUser(roles)) {
				url = "/home";
			}
			
				return url;
	}
// Kiểm tra roles chứa ADMIN HOẶC USER
	private boolean isAdmin(List<String> roles) {
		if (roles.contains("ADMIN")) {
			return true;
		}
		return false;
	}
	
	private boolean isUser(List<String> roles) {
		if (roles.contains("USER")) {
			return true;
		}
		return false;
	}
}
