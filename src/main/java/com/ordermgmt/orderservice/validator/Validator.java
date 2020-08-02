package com.ordermgmt.orderservice.validator;

import com.ordermgmt.orderservice.domain.ErrorResponse;

public interface Validator {

	ErrorResponse validate(Object objectToValidate);
}
