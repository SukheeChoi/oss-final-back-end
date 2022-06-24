package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Packing {
	private int orderItemNo;
	private int boxQty;
	private String employeeId;
	private Date dateTime;
	private String note;
	private int unrelease;
}
