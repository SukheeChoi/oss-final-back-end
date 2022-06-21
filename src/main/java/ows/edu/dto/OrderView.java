package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class OrderView {

	private int orderItemNo;
	private Date orderDate;         //주문일시
	private int orderNo;        //주문번호
	private String clientName;       //거래처번호
	private String itemName;
	private String itemCode;
	private int orderItemQuantity;
	private String orderShippingWay;
	private String VendorName;
	private String PickingDirectionAttempt;
	private String PickingDirectionDate;
	private String PickingDirectionQuantity;
	private String PickingDirectionUnrelease;
	
}