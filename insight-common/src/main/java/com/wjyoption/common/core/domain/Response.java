package com.wjyoption.common.core.domain;

import java.io.Serializable;

public class Response<T> implements Serializable {

	private static final long serialVersionUID = -99567317049134324L;

	private T data;

	private int retCode;

	private String message;

	private Long total;
	
	public Response(){
	}
	public Response(int retCode,String message){
		this.retCode = retCode;
		this.message = message;
	}

	public int getRetCode() {
		return retCode;
	}

	public void setRetCode(int retCode) {
		this.retCode = retCode;

	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}

}