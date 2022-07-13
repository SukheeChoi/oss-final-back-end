package ows.edu.dto;

import lombok.Data;

@Data
public class OrderItem {
	private int orderItemNo;
	private Long orderNo;
	private String itemCode;
	private int qty;
	private int unreleaseQuantity;
	private String orderItemNote;
}
