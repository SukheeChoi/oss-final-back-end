package ows.edu.dto;

import lombok.Data;

@Data
public class OrderItem {
	private int orderItemNo;
	private int orderNo;
	private String code;
	private int qty;
	
	
}
