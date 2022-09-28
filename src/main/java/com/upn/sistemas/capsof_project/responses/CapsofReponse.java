package com.upn.sistemas.capsof_project.responses;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "This model is the envelope")
public class CapsofReponse<T> implements Serializable {

	private static final long serialVersionUID = 8506477341552925019L;
	
	private String status;
	private String code;
	private String message;
	private T data;

	public CapsofReponse() {
		super();
	}

	public CapsofReponse(String status, String code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}

	public CapsofReponse(String status, String code, String message, T data) {
		this.status = status;
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return (T) this.data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String toString() {
		return "CapsofReponse [status=" + this.status + ", code=" + this.code + ", message=" + this.message
				+ ", data=" + this.data + "]";
	}

}
