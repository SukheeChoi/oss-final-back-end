package ows.edu.dto;

import lombok.Data;

@Data
public class Box {
	String releaseCode;			//출고번호
	int boxNumber; 				//n번째 박스
	String itemCode;			//아이템코드
	String itemName;			//아이템이름
	int releaseInspectionQty;	//검수수량
	int pickingQty;				//피킹수량
	int orderItemQty;			//주문수량
	int orderItemNo;			//
}
