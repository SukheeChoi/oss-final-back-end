package ows.edu.dto;

import lombok.Data;

//품목
@Data
public class ItemDto {
	private String itemCode;	//품목코드PK
	private String itemName;	//품목명
	private int partnerNo;		//업체번호FK
}
