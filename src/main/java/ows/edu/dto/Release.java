package ows.edu.dto;

import java.util.Date;

import lombok.Data;

//출고
@Data
public class Release {
	private int releaseNo;			//출고번호
	private int orderItemNo;		//주문상품번호
	private String personInCharge;	//담당자
	private String deliveryCompany;	//택배사
	private String invoiceNo;		//송장번호
	private Date releasedate;		//출고일시
}
