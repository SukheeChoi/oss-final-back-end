package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Release {
	private String releaseCode;
	private Long orderNo;
	private String employeeId;
	private Date releaseDate;
	private String invoiceCode; //송장번호
	private String shippingCompany; //배송회사
	private int unrelease;
	private int boxQuantity;
	
	private String employeeName;
}
