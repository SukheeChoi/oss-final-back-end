package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Picking {
	private int pickingNo;
	private int orderItemNo;
	private String employeeId;
	private int pickingQuantity;
	private Date date;
	
}
