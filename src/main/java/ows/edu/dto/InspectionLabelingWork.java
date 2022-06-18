package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class InspectionLabelingWork {
	private int placeOrderNo;          //발주번호는 숫자고 앞에 P붙이기?? 뭔가 String으로 바꿔야 할 거 같음
	private String employeeId;
	private Date receiveMonthDay;
	private Date receiveHourMinute;
	private int receiveItem;
	private int receiveQuentity;
	private Date scheduledStartTime;
	private Date scheduledEndTime;
	private Date startTime;
	private Date workTime;
	private int progressRate;
	private int status;
	private int lateTime;
	private int inspectionQuentity;
	private int passItemQuentity;
	private int labelingItemQuentity;
	
}
