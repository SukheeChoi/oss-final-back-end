package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Order {
	private int orderNo;						//주문번호
	private Date date;					//주문일시
	private String shippingWay;			//배송방식(합배송/오스템)
	private int status;					//주문상태
	private String shippingDestination;	//배송지
	private String shippingAddress;		//주소
	private String orderWay;			//주문방법
	private String shippingCategory; //배송방식(긴급/일반)
}
