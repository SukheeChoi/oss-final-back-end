package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Packing {
	private int orderItemNo;
	private int boxQty;
	private String employeeId;
	private Date dateTime;
	private int unrelease;

	// 미출고 수량이 null인 경우에 공백문자를 grid 셀에 표시하기 위함.
	private String strUnreleased;
}
