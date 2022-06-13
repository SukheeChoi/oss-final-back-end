package ows.edu.dto;

import java.util.Date;

import lombok.Data;

//피킹지시
@Data
public class PickingDirection {
	private int orderItemNo;		//주문번호
	private int attempt;			//차수
	private Date directionDate;		//지시일시
	private int directionQty;		//지시수량
	private int unReleased;			//미출고
	private String personInCharge;	//담당자
}
