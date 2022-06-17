package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ReleaseInspection {	
	private int orderItemNo;
	private int releaseInspectionQuentity;
	private Date releaseInspectionDate;
	private Date releasePrintDate;
	private Date receiptePrintDate;
	private String employeeId;
	private int releaseInspectionBarcode;
	private String releaseInspectionNote;
	private int unReleased;
}
