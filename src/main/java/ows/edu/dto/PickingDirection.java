package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PickingDirection {
	private int orderItemNo;
	private int attempt;
	private Date date;
	private int qty;
	private int unrelease;
	private String employeeId;
	private int lotLocationNo;
}
