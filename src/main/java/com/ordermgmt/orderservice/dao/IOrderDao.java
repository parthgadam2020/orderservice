package com.ordermgmt.orderservice.dao;

import java.util.List;

import com.ordermgmt.orderservice.dto.OrderDTO;
import com.ordermgmt.orderservice.dto.ProductCodeDTO;

public interface IOrderDao {

	 /**
	  * create Order
	  * 
	  * @param OrderDTO
	  * @return boolean
	  */
	 public Integer createOrder(OrderDTO orderDTO);
	 
	 /**
	  * Get order by order id
	  * @return OrderDTO
	  */
	 public OrderDTO getOrderByOrderId(Integer orderId, List<ProductCodeDTO> productCodeList);
}
