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
	private String invoiceCode; //송장번호
	private String note; //메모
	private String shippingCompany; //배송회사
	private int unrelease;
	private int boxQuantity;
	
	private String employeeName;
}
