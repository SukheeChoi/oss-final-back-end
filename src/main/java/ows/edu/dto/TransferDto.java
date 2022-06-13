package ows.edu.dto;

import java.util.Date;

import lombok.Data;

//인계
@Data
public class TransferDto {
	private int orderItemNo;		//주문품목번호
	private String personInCharge;	//담당자
	private Date transferDate;		//인계일시
}
