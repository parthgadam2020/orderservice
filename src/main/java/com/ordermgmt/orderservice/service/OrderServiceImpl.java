package com.ordermgmt.orderservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ordermgmt.orderservice.common.OrderServiceUtil;
import com.ordermgmt.orderservice.dao.IOrderDao;
import com.ordermgmt.orderservice.dto.OrderDTO;
import com.ordermgmt.orderservice.dto.ProductCodeDTO;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService{

	@Autowired
	IOrderDao iOrderDao;
	
	@Autowired
	private ServiceFeign service;

	/**
	 * {@inheritDoc}
	 */
	public boolean createOrder(OrderDTO orderDTO) {
		Integer orderId = iOrderDao.createOrder(orderDTO);
		service.createOrderItem(orderDTO.getProductCodeList(), orderId);
		if (OrderServiceUtil.hasId(orderId)) {
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public OrderDTO getOrderByOrderId(Integer orderId) {
	List<ProductCodeDTO> productCodeList = service.getOrderItemListByOrderId(orderId);
		return iOrderDao.getOrderByOrderId(orderId, productCodeList);
	}

}
