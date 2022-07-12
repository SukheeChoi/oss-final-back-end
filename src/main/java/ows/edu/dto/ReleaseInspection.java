package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ReleaseInspection {	
	private int orderItemNo;
	private int releaseInspectionQuantity;
	private Date releaseInspectionDate;
	private Date releasePrintDate;
	private Date receiptPrintDate;
	private String employeeId;
	private long releaseInspectionBarcode;
	private int unReleased;
	
	private String employeeName;

}
