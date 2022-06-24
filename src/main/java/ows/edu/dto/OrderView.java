package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class OrderView {

	private int orderItemNo;
	private String orderDate;         //주문일시
	private int orderNo;        //주문번호
	private String clientName;       //거래처번호
	private String itemName;
	private String itemCode;
	private int orderItemQuantity;
	private String shippingCategory;
	private String VendorName;
	private String PickingDirectionAttempt;
	private String PickingDirectionDate;
	private String PickingDirectionQuantity;
	private String PickingDirectionUnrelease;
	private String orderShippingWay;
	private String orderCheckDate;
	private int releaseQuantity;
	private String releaseScheduleDate;
	private String recieveDate;
	
}