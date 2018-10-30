package org.bluesky.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class LoggerInterceptor implements HandlerInterceptor {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggerInterceptor.class);
	
	// 进入 Handler方法之前执行
	// 用于身份认证、身份授权
	// 比如身份认证，如果认证不通过表示当前用户没有登陆，需要此方法拦截不再向下执行
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		LOGGER.info("LoggerInterceptor...preHandle");
		
		LOGGER.info("日志拦截器捕获求取URL信息：" + request.getRequestURI());
		return true;
	}

	// 进入Handler方法之后，返回modelAndView之前执行
	// 应用场景从modelAndView出发：将公用的模型数据(比如菜单导航)在这里传到视图，也可以在这里统一指定视图
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		LOGGER.info("LoggerInterceptor...postHandle");
		
		String view = "";
		if (modelAndView != null && !"null".equals(modelAndView)) {
			view = modelAndView.getViewName();
		}
		LOGGER.info("日志拦截器捕获调整视图信息：" + view);
	}

	// 执行Handler完成执行此方法
	// 应用场景：统一异常处理，统一日志处理
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		LOGGER.info("LoggerInterceptor...afterCompletion");
		
		if (ex != null)
			LOGGER.info("日志拦截器捕获异常信息：" + ex.getMessage());
	}

}
