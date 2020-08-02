package com.ordermgmt.orderservice.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.ordermgmt.orderservice.common.AppConstant;
import com.ordermgmt.orderservice.domain.Error;
import com.ordermgmt.orderservice.domain.ErrorResponse;
import com.ordermgmt.orderservice.dto.OrderDTO;

@Component(value = "orderValidator" )
public class OrderValidatorImpl implements Validator{

	@Override
	public ErrorResponse validate(Object objectToValidate) {
		
	ErrorResponse validationResponse = new ErrorResponse();
	OrderDTO orderDTO = (OrderDTO) objectToValidate;
	
			if(StringUtils.isEmpty(orderDTO.getCustomerName())) {
				validationResponse.addError(new Error(AppConstant.STR_CUSTOMER_NAME_NOT_EMPTY));
			}
			
			if(StringUtils.isEmpty(orderDTO.getOrderDate())) {
				validationResponse.addError(new Error(AppConstant.STR_ORDER_DATE_NOT_EMPTY));
			}
			
			if(StringUtils.isEmpty(orderDTO.getShippingAddress())) {
				validationResponse.addError(new Error(AppConstant.STR_SHIPPING_ADDRESS_NOT_EMPTY));
			}
			
			if(CollectionUtils.isEmpty(orderDTO.getProductCodeList())) {
				validationResponse.addError(new Error(AppConstant.STR_PRODUCT_LIST_NOT_EMPTY));
			}
		
		return validationResponse;
	}

}
