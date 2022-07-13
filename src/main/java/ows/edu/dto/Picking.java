package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Picking {
	private Integer pickingNo;
	private Integer orderItemNo;
	private String employeeId;
	private Integer pickingQuantity;
	private Date date;
	
}
