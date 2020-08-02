package com.ordermgmt.orderservice.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ordermgmt.orderservice.domain.ServiceResponse;
import com.ordermgmt.orderservice.dto.ProductCodeDTO;

@FeignClient(name="orderitemservice", url="localhost:8036/api/orderitemservice")
public interface ServiceFeign {

	@GetMapping("/getAllOrderItem")
	ServiceResponse getAllOrderItem();
	
	@GetMapping("/getOrderItemIdByProductCode/{productCd}")
	Integer getOrderItemIdByProductCd(@PathVariable String productCd);
	
	@GetMapping("/getOrderItemListByOrderId/{orderId}")
	List<ProductCodeDTO> getOrderItemListByOrderId(@PathVariable Integer orderId);
	
	@PostMapping("/createOrderItem/{orderId}")
	ServiceResponse createOrderItem(@RequestBody List<ProductCodeDTO> productCodeList, @PathVariable Integer orderId);
}
