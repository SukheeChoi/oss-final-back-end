package ows.edu.dto;

import lombok.Data;

@Data
public class OrderItem {
	private Integer orderItemNo;
	private Long orderNo;
	private String itemCode;
	private int qty;
	private Integer unreleaseQuantity;
	private String orderItemNote;
}
