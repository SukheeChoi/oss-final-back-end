package ows.edu.dto;

import lombok.Data;

@Data
public class PlacingOrderItem {
	private int placingOrderItemNo;
	private int PlacingOrderNo;
	private String itemCode;
	private int PlacingOrderItemQuentity;
}
