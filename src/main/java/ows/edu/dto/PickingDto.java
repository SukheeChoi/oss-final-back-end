package ows.edu.dto;

import java.util.Date;

import lombok.Data;

//피킹
@Data
public class PickingDto {
	private int pikingNo;			//피킹번호
	private int orderItemNo;		//주문품목번호
	private String personInCharge;	//담당자
	private int pickingQty;			//피킹수량
	private Date pickingDate;		//피킹일시
	private int unreleased;			//미출고
}
