package com.stockManager;

public class priceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;

	public String getMsg() {
		return msg;
	}

	public priceException(String msg) {
		this.msg = msg;
	}
}
