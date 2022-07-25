package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class InspectionLabelingWork {   //왼쪽 화면(3번)
	private String placingOrderNo;
	private String title;        //여기서는 담당자가 아니라 업체명이 들어감

	private String receiveDate;
	private int receiveItem;
	private int receiveQuantity;
	private String scheduledStartTime;
	private String scheduledEndTime;
	private String scheduledTime;        //정제된 예정 시간
	private int totalWorkTime; 
	private String startTime;
	private String workTime;
	private String progressRate;
	private String status;
	private String lateTime;
	private Integer inspectionQuantity;
	private Integer passItemQuantity;
	private Integer labelingItemQuantity;
	
	private String employeeName;
}
