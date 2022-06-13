package ows.edu.dto;

import java.util.Date;

import lombok.Data;

//협력사로 부터 제공받은 정보
@Data
public class PartnersInfo {
	private int partnerNo;			//업체번호
	private int orderItemNo;		//주문품목번호
	private Date orderCheckDate;	//주문확인일시
	private int releasedQty;		//출고수량
	private Date releaseSchedule;	//출고예정일자
	private Date receiveDate;		//수령일시
}
