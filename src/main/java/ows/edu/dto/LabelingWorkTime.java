package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class LabelingWorkTime {
	private String employeeId;
	private Date labelingWorkTimeDate;
	private int totalProgressRate;
	private int receiveItem;
	private int receiveQuentity;
	private Date scheduledHourMinute;
	private Date scheduledStartTime;
	private Date endTime;
	private String totalWorkTime;
	private int LWTNine;
	private int LWTTen;
	private int LWTEleven;
	private int LWTThirteen;
	private int LWTFourteen;
	private int LWTFifteen;
	private int LWTSixteen;
	private int LWTSeventeen;
}
