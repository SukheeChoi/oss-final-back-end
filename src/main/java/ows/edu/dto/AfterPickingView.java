package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class AfterPickingView {	
	private String releaseNo;
	private String itemName;
	private int pickingNo;
	private int pickingQuantity;
	private int releaseInspectionQuantity;
	private int releaseInspectionUnreleased;
	private int packingUnreleased;
	private String vendorName;
	private String shippingDestination;
	private String shippingCategory;
	private String shippingWay;
	private String releaseInspectionAssignee;
	private Date releasePrintDate;
	private Date receiptPrintDate;
	private Date releaseInspectionDate;
	private int packingBoxQuantity;
	private String releaseAssignee;
	private String shippingCompany;
	private String invoiveCode;
	private String releaseInspectionNote;
	private String releaseNote;
	private Boolean releaseDone;
	private int orderNo;
	private String clientName;
	private int orderItemNo;
}
