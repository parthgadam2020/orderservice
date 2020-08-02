package com.ordermgmt.orderservice.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.ordermgmt.orderservice.common.OrderServiceUtil;
import com.ordermgmt.orderservice.common.QueryConstant;
import com.ordermgmt.orderservice.dto.OrderDTO;
import com.ordermgmt.orderservice.dto.ProductCodeDTO;

@Repository
public class OrderDaoImpl implements IOrderDao {

	private static final Logger LOGGER = Logger.getLogger(OrderDaoImpl.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	/**
	 * {@inheritDoc}
	 */
	public Integer createOrder(OrderDTO orderDTO) {

		String[] primarKey = { "ORDER_ID" };
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(con -> {
			PreparedStatement ps = con.prepareStatement(QueryConstant.INSERT_NEW_ORDER, primarKey);
			ps.setString(1, orderDTO.getCustomerName());
			ps.setDate(2, Date.valueOf(OrderServiceUtil.convertyyyyMMdd_toyyyy_MM_dd(orderDTO.getOrderDate())));
			// ps.setString(2, orderDTO.getOrderDate());
			ps.setString(3, orderDTO.getShippingAddress());
			ps.setInt(4, orderDTO.getProductCodeList().size());
			return ps;
		}, keyHolder);

		return (Integer) keyHolder.getKey();
	}

	/**
	 * {@inheritDoc}
	 */
	public OrderDTO getOrderByOrderId(Integer orderId, List<ProductCodeDTO> productCodeList) {
		OrderDTO orderDTO = null;
		try {
			orderDTO = jdbcTemplate.queryForObject(QueryConstant.GET_ORDER_BY_ORDER_ID,
					new BeanPropertyRowMapper<OrderDTO>(OrderDTO.class), new Object[] { orderId });
			if (!CollectionUtils.isEmpty(productCodeList)) {
				orderDTO.setProductCodeList(productCodeList);
			}

		} catch (EmptyResultDataAccessException e) {
			LOGGER.debug("Error while getOrderItemById in OrderItemDaoImpl, " + e.getMessage());
		}
		return orderDTO;
	}
}
