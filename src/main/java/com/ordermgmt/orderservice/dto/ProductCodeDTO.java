package com.ordermgmt.orderservice.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductCodeDTO {
	
	private String productCode;
	
	private String productName;
	
	private Integer quantity;
}
