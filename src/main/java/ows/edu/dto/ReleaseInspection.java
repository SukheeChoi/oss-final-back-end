package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ReleaseInspection {	
	private int orderItemNo;
	private int releaseInspectionQuantity;
	private Date releaseInspectionDate;
	private Date releasePrintDate;
	private Date receiptePrintDate;
	private String employeeId;
	private long releaseInspectionBarcode;
	private String releaseInspectionNote;
	private int unReleased;
	
	private String employeeName;
	
	private Release release;
	private Item item;
	private Picking picking;
	private Vendor vendor;
	private Order order;
	private Employee employee;
	private Packing packing;
}
