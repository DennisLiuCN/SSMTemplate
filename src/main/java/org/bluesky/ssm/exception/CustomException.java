package org.bluesky.ssm.exception;

/**
 * 自定义异常类，针对预期的异常，需要在程序中抛出此类的异常
 * @author: liuyuefeng
 * @date: 2015-6-3 下午6:29:30
 * @version: V1.0
 * 
 */
public class CustomException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	// 异常信息
	public String message;

	public CustomException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
