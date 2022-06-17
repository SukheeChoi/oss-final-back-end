package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class LabelingWorkTime {
	private String employeeId;           //담당자
	private Date labelingWorkTimeDate;   //수령일
	private int totalProgressRate;       //전체 진행률
	private int receiveItem;             //수령 품목
	private int receiveQuentity;         //수령 수량
	private Date scheduledStartTime;     //예정 시작 시간
	private Date scheduledendTime;       //예정 완료 시간
  private Date scheduledHourMinute;    //예정 완료 시간 - 예정 시작 시간(07:00)
	private String totalWorkTime;        //작업 시간
	private int LWTNine;                 //9시
	private int LWTTen;                  //10시
	private int LWTEleven;               //11시
	private int LWTThirteen;             //13시
	private int LWTFourteen;             //14시
	private int LWTFifteen;              //15시
	private int LWTSixteen;              //16시
	private int LWTSeventeen;            //17시
}
