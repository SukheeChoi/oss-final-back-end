package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ReleaseInspection {	
	private Integer orderItemNo;
	private Integer releaseInspectionQuantity;
//	private String releaseInspectionDate;
//	private String releasePrintDate;
//	private String receiptPrintDate;
	private Date releaseInspectionDate;
	private Date releasePrintDate;
	private Date receiptPrintDate;
	private String employeeId;
	private long releaseInspectionBarcode;
	
//	private String employeeName;

}
