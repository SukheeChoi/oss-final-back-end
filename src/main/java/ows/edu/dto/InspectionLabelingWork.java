package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class InspectionLabelingWork {   //왼쪽 화면
	private String placeOrderNo;          //발주번호는 숫자고 앞에 P붙이기?? 뭔가 String으로 바꿔야 할 거 같음
	private int labelingWorkTimeNo;
	private String employeeName;        //여기서는 담당자가 아니라 업체명이 들어감
	private Date receiveMonthDay;       //서칭 편하게 하려고 나누었는데 굳이 안나누어도 서칭 가능해서 빼는게 나을듯
	private String receiveHourMinute;
	private int receiveItem;
	private int receiveQuantity;
	private String scheduledStartTime;
	private String scheduledEndTime;
	private String scheduledTime;        //정제된 예정 시간
	private int totalWorkTime; 
	private String startTime;
	private String workTime;
	private Integer progressRate;
	private String status;
	private Integer lateTime;
	private Integer inspectionQuantity;
	private Integer passItemQuantity;
	private Integer labelingItemQuantity;
}
