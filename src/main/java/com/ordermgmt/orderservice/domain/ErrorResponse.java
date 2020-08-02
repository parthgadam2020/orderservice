package com.ordermgmt.orderservice.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ErrorResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Error> errors = new ArrayList<Error>();
	
	private String status;
	
	public void addError(Error error) {
		getErrors().add(error);
	}
	
	public boolean hasError() {
		if(getErrors().isEmpty()) {
			return false;
		}
		return true;
	}
}
