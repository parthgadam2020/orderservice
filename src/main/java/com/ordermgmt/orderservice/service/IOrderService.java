package com.ordermgmt.orderservice.service;

import com.ordermgmt.orderservice.dto.OrderDTO;

public interface IOrderService {
	
 /**
  * create Order
  * 
  * @param OrderDTO
  * @return boolean
  */
 public boolean createOrder(OrderDTO orderDTO);
 
 /**
  * get order by order id
  * @return List<OrderDTO>
  */
 public OrderDTO getOrderByOrderId(Integer orderId);

}
