package ows.edu.dto;

import java.util.List;

import lombok.Data;

@Data
public class LabelingWorkTime {        //왼쪽화면(1번, 2번)
  private int labelingWorkTimeNo;      //번호
	private String title;         //담당자
	private String labelingWorkTimeDate; //작업일
	private int receiveItem;             //수령 품목
	private int receiveQuantity;         //수령 수량(전체 작업량)
	private int currentQuantity;         //현재 작업량
	private int progressQuantity;        //현재 진행량
	private String scheduledStartTime;   //예정 시작 시간
	private String scheduledEndTime;     //예정 완료 시간
	private String scheduledTime;        //정제된 예정 시간
	private int totalWorkTime;           //작업 시간(분)(예정 시작 시간 -  예정 완료 시간)
	private int totalProgressRate;       //전체 진행률
	private Integer LWTNine;             //9시
	private Integer LWTTen;              //10시
	private Integer LWTEleven;           //11시
	private Integer LWTThirteen;         //13시
	private Integer LWTFourteen;         //14시
	private Integer LWTFifteen;          //15시
	private Integer LWTSixteen;          //16시
	private Integer LWTSeventeen;        //17시
	
	
	private List<LabelingWorkTime> child;
	private List<InspectionLabelingWork> childrennn;
}
