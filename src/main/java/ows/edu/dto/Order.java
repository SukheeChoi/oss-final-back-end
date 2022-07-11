package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Order {
	private Long orderNo;				//주문번호
	private Date date;					//주문일시
	private int clientNo;				//고객번호
	private String shippingWay;			//배송방식(합배송/오스템)
	private int status;					//주문상태
	private String shippingDestination;	//배송지
	private String shippingAddress;		//주소
	private String orderWay;			//주문방법
	private String shippingCategory; 	//배송방식(긴급/일반)

	private boolean orderUnrelease;
	
	private Client client;
	private OrderItem orderItem;
	private PickingDirection pickingDirection;
	private Picking picking;
	private ReleaseInspection releaseInspection;
	private Release release;
	private Transfer transfer;
	
	private boolean processedOrder; //처리 완료된 주문.(인계완료? 고객에게 배송완료?)
}
