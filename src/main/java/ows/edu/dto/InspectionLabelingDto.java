package ows.edu.dto;

import lombok.Data;

@Data
public class InspectionLabelingDto {
	private int placingOrderItemNo;	//발주품목코드
	private int receivedQty;		//수령수량
	private int inspectionQty;		//검수수량
	private int passedQty;			//양품수량
	private int missingQty;			//누락수량
	private int demagedQty;			//파손수량
	private int etcQty;				//기타수량
	private int labelingQty;		//라벨링수량
	private int accepted;			//승인여부
	private String personInCharge;	//담당자
}
