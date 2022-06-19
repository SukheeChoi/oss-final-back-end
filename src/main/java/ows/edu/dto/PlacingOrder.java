package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PlacingOrder {
	private int placingOrderNo;
	private int vendorNo;
	private String employeeId;
	private Date placingOrderdate;
	private Date recieveDate;
}
