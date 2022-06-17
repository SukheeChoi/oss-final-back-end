package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Release {
	private int releaseNo;
	private int orderNo;
	private String employeeId;
	private Date releaseDate;
}
