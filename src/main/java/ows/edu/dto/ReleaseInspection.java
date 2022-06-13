package ows.edu.dto;

import java.util.Date;

import lombok.Data;

//출고검수&패킹
@Data
public class ReleaseInspection {
	private int orderItemNo;			//주문품목번호
	private String personInCharge;		//담당자
	private int inspectionQty;			//검수수량
	private int boxQty;					//box수량
	private Date inspectionDate;		//검수일시
	private Date releasePrintDate;		//출고요청서인쇄일시
	private Date transactionPrintDate;	//거래명세서인쇄일시
	private int unReleased;				//미출고
}
