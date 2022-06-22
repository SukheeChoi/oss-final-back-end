package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Release {
	private String releaseNo;
	private int orderNo;
	private String employeeId;
	private Date releaseDate;
	private Boolean releaseDone;
	private String invoiceCode;
	private String note;
	
	private String employeeName;
}
