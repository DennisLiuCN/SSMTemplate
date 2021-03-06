package org.bluesky.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登陆认证拦截器
 * @author: liuyuefeng
 * @date: 2015-6-4 下午2:40:37
 * @version: V1.0
 * 
 */
public class LoginInterceptor implements HandlerInterceptor {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);
	
	// 进入 Handler方法之前执行
	// 用于身份认证、身份授权
	// 比如身份认证，如果认证不通过表示当前用户没有登陆，需要此方法拦截不再向下执行
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		LOGGER.info("LoginInterceptor...preHandle");
		
		// 获取请求的url
		String url = request.getRequestURI();
		// 判断url是否是公开地址（实际使用时将公开地址配置配置文件中）
		// 这里公开地址是登陆提交和查询的地址
		if (url.indexOf("login") >= 0 || url.indexOf("queryItems") >= 0) {
			// 如果进行登陆提交或者查看商品，放行
			return true;
		}

		// 判断session
		HttpSession session = request.getSession();
		// 从session中取出用户身份信息
		String username = (String) session.getAttribute("username");

		if (username != null) {
			// 身份存在，放行
			return true;
		}

		// 执行这里表示用户身份需要认证，跳转登陆页面
		request.getRequestDispatcher("/loginout/login").forward(request, response);
		LOGGER.info("无权进行该操作，系统自动进行调整！");

		// return false表示拦截，不向下执行
		// return true表示放行
		return false;
	}

	// 进入Handler方法之后，返回modelAndView之前执行
	// 应用场景从modelAndView出发：将公用的模型数据(比如菜单导航)在这里传到视图，也可以在这里统一指定视图
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		LOGGER.info("LoginInterceptor...postHandle");
	}

	// 执行Handler完成执行此方法
	// 应用场景：统一异常处理，统一日志处理
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		LOGGER.info("LoginInterceptor...afterCompletion");
	}

}
