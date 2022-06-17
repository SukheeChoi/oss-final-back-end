package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Transfer {
	private int releaseNo;
	private int orderNo;
	private String employeeId;
	private Date transferDate;
}
