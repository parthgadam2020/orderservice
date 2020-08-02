package com.ordermgmt.orderservice.common;

public interface QueryConstant {
	
	/** get all order */
	String GET_ORDER_BY_ORDER_ID = " SELECT customer_name as customerName, order_date as orderDate, "
			+ " shipping_address as shippingAddress, total as total "
			+ " FROM orders where order_id = ? ";
	
	/** insert clause new order  */
	String INSERT_NEW_ORDER = " insert into orders(CUSTOMER_NAME, ORDER_DATE, SHIPPING_ADDRESS, TOTAL) "
			+ " VALUES( ?,?,?,?) ";
	
	/** insert clause order item map  */
	String INSERT_ORDER_ITEM_MAP = "INSERT INTO order_item_map( "
			+ " order_id, order_item_id) " 
			+ " VALUES( ?,?)";
}
