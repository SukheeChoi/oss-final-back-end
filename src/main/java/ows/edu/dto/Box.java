package ows.edu.dto;

import lombok.Data;

@Data
public class Box {
	private int boxNo;					//pk
	private String releaseCode;			//출고번호
	private int orderItemNo;			//oi_no
	private int boxNumber; 				//n번째 박스
	private int boxItemQuantity;		//박스에 들어가 있는 물건 개수	
}
