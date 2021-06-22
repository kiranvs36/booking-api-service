package com.bookingapp.service.impl;

public class ApiResponse {
	
	private int code;
	private Object data;
	public static final int SUCCESS = 200;
	public static final int FAILED = 500;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	

}
