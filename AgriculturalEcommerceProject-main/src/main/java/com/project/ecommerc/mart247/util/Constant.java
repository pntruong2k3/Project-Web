package com.project.ecommerc.mart247.util;

import com.project.ecommerc.mart247.config.Properties;

public interface Constant {
	public static final Properties properties = new Properties();
	/* public static final HttpServletRequest request = null; */

    public interface KEY_ATTRIBUTE{
        String MESSENGER = "msg";
    }
    public interface ERROR{
        String COMMON_ERROR = "Có lỗi xảy ra";
    }
    public interface SUCCESS{
        String RESET_PASS_SUCCESS = "Đặt lại mật khẩu thành công";
        String REGISTER_SUCCESS = "Đăng ký thành công";
    }
    public interface MESSENGER{
        String ACTIVE_USER = "msg";
    }
    
    public interface ENPOINT{
    	String ADD_USER = "/add";
    }
    
    public interface SENDMAIL{
    	String ADD_USER = "Xác nhận đăng ký tài khoản của bạn hãy bấm vào ";
    }
    
	/*
	 * public interface URL{ String GET_URL = request.getRequestURL().toString();; }
	 */
}
