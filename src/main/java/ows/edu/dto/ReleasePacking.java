package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ReleasePacking {
	private int no;
	
	//order
	private String orderNo;				//주문번호
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
	private int orderItemQuantity;
	private int oderItemUnreleaseQuantity;
	
	//Item
	private String itemName;
	private int vendorNo;
	
	//Release
	private String releaseCode;
	private String done;				//출고검수 여부
	private int releaseBoxQty;
	private long releaseBarcode;
	
	//ReleaseInspection
	private int releaseInspectionQuantity;
	private String note;
	private Date releasePrintDate;
	private Date receiptePrintDate;
	private int unReleased;
	
	//picking
	private int pickingQty;
	
	//vendor
	private String vendorName;
	
	//employee
	private String employeeName;
	
	//client
	private String clientName;
	
	//barcode
	private int barCode;
	private int barCodeDone;
	
	//box
	private int boxItemQuantity;
}
