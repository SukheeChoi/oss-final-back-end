package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class InspectionLabelingWork {   //왼쪽 화면
	private String placeOrderNo;          //발주번호는 숫자고 앞에 P붙이기?? 뭔가 String으로 바꿔야 할 거 같음
	private String employeeId;
	private Date receiveMonthDay;
	private Date receiveHourMinute;
	private int receiveItem;
	private int receiveQuantity;
	private Date scheduledStartTime;
	private Date scheduledEndTime;
	private Date startTime;
	private Date workTime;
	private int progressRate;
	private String status;
	private int lateTime;
	private int inspectionQuantity;
	private int passItemQuantity;
	private int labelingItemQuantity;
	
}
