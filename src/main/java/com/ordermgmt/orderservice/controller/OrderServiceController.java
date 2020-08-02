package com.ordermgmt.orderservice.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ordermgmt.orderservice.common.OrderServiceUtil;
import com.ordermgmt.orderservice.domain.ErrorResponse;
import com.ordermgmt.orderservice.domain.ServiceResponse;
import com.ordermgmt.orderservice.dto.OrderDTO;
import com.ordermgmt.orderservice.service.IOrderService;
import com.ordermgmt.orderservice.validator.Validator;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/orderservice")
public class OrderServiceController {
	
	private static final Logger LOGGER = Logger.getLogger(OrderServiceController.class);
	
	@Autowired
	IOrderService iOrderService;
	
	@Autowired
	@Qualifier(value = "orderValidator")
	private Validator orderValidator;
	
	@ApiOperation(value = "Create New Order", notes = "Create New Order.")
	@RequestMapping(value = "/createOrder", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ServiceResponse createOrder(@RequestBody OrderDTO orderDTO) {
		LOGGER.debug("...in .createOrder controller start.");
		ErrorResponse validationResponse = orderValidator.validate(orderDTO);
		if(!validationResponse.hasError()) {
			boolean f = iOrderService.createOrder(orderDTO);
			return OrderServiceUtil.getServiceResponse(f);
		}else {
			return OrderServiceUtil.getServiceResponse(validationResponse.getErrors());
		}
		
	}
	
	@ApiOperation(value="get Order By Order Id", notes="get order by order id")
	@RequestMapping(value = "/getOrderByOrderId/{orderId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ServiceResponse getOrderByOrderId(@PathVariable Integer orderId) {
		LOGGER.debug("...in .getOrderByOrderId controller start.");
		OrderDTO list = iOrderService.getOrderByOrderId(orderId);
		return OrderServiceUtil.getServiceResponse(list);
	}
}
