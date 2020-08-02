package com.ordermgmt.orderservice.domain;

import java.io.Serializable;

import lombok.Data;
@Data
public class Error implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String errorMessage;
	
	public Error(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
