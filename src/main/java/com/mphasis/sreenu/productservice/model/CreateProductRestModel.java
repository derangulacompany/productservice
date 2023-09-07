package com.mphasis.sreenu.productservice.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CreateProductRestModel {
	
	private String tittle;
	private BigDecimal price;
	private Integer quantity;

}
