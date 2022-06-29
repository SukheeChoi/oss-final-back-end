package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Picking {
	private int pickingNo;
	private int pickingQuantity;
	private int orderItemNo;
	private String employeeId;
	private String locationCode;
	private Date date;
	private Boolean done;
	
}
