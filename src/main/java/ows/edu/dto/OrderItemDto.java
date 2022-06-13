package ows.edu.dto;

import lombok.Data;

//주문 상품
@Data
public class OrderItemDto {
	private int orderItemNo;	//주문상품번호
	private int orderNo;		//주문번호
	private int orderQty;		//주문수량
	private int shippingCat;	//배송구분
	private String itemcode;	//
}
