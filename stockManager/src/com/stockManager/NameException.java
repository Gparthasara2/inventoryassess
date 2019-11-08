package com.stockManager;

public class NameException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;

	public String getMsg() {
		return msg;
	}

	public NameException(String msg) {
		this.msg = msg;
	}
}
