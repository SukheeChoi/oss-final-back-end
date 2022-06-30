package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ReleaseInspectionView {
	private int no;
	
	//order
	private int orderNo;				//주문번호
	private Date date;					//주문일시
	private int clientNo;				//거래처번호
	private String shippingWay;			//배송방식(합배송, 직배송)
	private int status;					//주문상태
	private String shippingDestination;	//배송지
	private String shippingAddress;		//주소
	private String orderWay;			//주문방법
	private String category;			//긴급, 일반
	
	//order Item
	private int orderItemNo;			
	private String code;
	private int orderItemqty;
	
	//Item
	private String itemName;
	private int vendorNo;
	
	//Release
	private String releaseCode;
	private int done;
	private int boxQty;
	
	//ReleaseInspection
	private int releaseInspectionQuantity;
	private String note;
	private Date releasePrintDate;
	private Date receiptePrintDate;
	private long releaseInspectionBarcode;
	private int unReleased;
	
	//picking
	private int pickingQty;
	
	//vendor
	private String vendorName;
	
	//employee
	private String employeeName;
	
	//client
	private String clientName;
}
