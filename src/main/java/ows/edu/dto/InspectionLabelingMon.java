package ows.edu.dto;

import java.util.Date;

import lombok.Data;

//검품검수 및 라벨링 모니터링
@Data
public class InspectionLabelingMon {
	private String placingOrderNo;		//업무번호??
	private String personInCharge;		//담당자
	private String receiveDate;			//수령일
	private Date scheduleStartTime;		//예정시작시간
	private Date scheduleEndTime;		//예정종료시간
	private Date startTime;				//시작시간
	private Date endTime;				//종료시간
}
