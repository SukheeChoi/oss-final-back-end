package ows.edu.dto;

import java.util.Date;

import lombok.Data;

//주문
@Data
public class Order {
	private int orderNo;		 //주문일시
	private Date orderDate;		 //주문일시
	private String orderClient;  //거래처
	private String shippingWay;  //배송방식
	private String shippingDest; //배송지
}
