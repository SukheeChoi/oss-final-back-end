package ows.edu.dto;

import lombok.Data;

//발주정보
@Data
public class PlacingOrderInfo {
	private int placingOrderItemNo;	//발주품목코드 
	private String placingOrderNo;	//발주번호
	private int placingQty;			//발주수량
	private String itemCode;		//품목코드
	private String lotNo;			//LOT번호
}
