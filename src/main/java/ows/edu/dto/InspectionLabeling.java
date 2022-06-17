package ows.edu.dto;

import lombok.Data;

@Data
public class InspectionLabeling {
	private int placeOrderItemNo;
	private String employeeNo;
	private int inspectionQuentity;
	private int passItemQuentity;
	private int missItemQuentity;
	private int damageItemQuentity;
	private int etcItemQuentity;
	private Boolean accept;
	private int labelingItemQuentity;
}
