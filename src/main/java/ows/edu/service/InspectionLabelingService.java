package ows.edu.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import ows.edu.dao.InspectionLabelingDao;
import ows.edu.dto.InspectionLabeling;
import ows.edu.dto.InspectionLabelingStatus;
import ows.edu.dto.InspectionLabelingView;
import ows.edu.dto.InspectionLabelingWork;
import ows.edu.dto.LabelingWorkTime;
import ows.edu.dto.Pager;

@Service
@Log4j2
public class InspectionLabelingService {

  @Autowired
  private InspectionLabelingDao inspectionLabelingDao;

  // (왼쪽) 트리 그리드 데이터 합치는 곳
  public LabelingWorkTime getTreeList() {

    int labelingWorkTimeNo = 0;
    int totalitem = 0;
    int totalQuantity = 0;
    
    // 2번째 담당자별 리스트
    List<LabelingWorkTime> employeeList = new ArrayList<>();
    employeeList.addAll(inspectionLabelingDao.searchAllByName());
    
    for (LabelingWorkTime employee : employeeList) {

      // 담당자별 예정 시간 정제
      String startTime = employee.getScheduledStartTime();
      String endTime = employee.getScheduledEndTime();
      int totalTime = employee.getTotalWorkTime();
      String scheduledTime = setDateTime(startTime, endTime, totalTime);
      employee.setScheduledTime(scheduledTime);

      // 3번째 업체별 리스트
      List<InspectionLabelingWork> workList = new ArrayList<>();
      labelingWorkTimeNo = employee.getLabelingWorkTimeNo();
      workList.addAll(inspectionLabelingDao.searchAllByEmployeeName(labelingWorkTimeNo));
      for (InspectionLabelingWork labelingWork : workList) {

        // 업체별 예정 시간 정제
        startTime = labelingWork.getScheduledStartTime();
        endTime = labelingWork.getScheduledEndTime();
        totalTime = labelingWork.getTotalWorkTime();
        scheduledTime = setDateTime(startTime, endTime, totalTime);
        labelingWork.setScheduledTime(scheduledTime);
      }
      employee.setChildrennn(workList);
    }

    // 1번째 전체 리스트
    LabelingWorkTime totalList = inspectionLabelingDao.searchAllByTotal();
    
    //전체 예정 시간 정제
    String startTime = totalList.getScheduledStartTime();
    String endTime = totalList.getScheduledEndTime();
    int totalTime = totalList.getTotalWorkTime();
    String scheduledTime = setDateTime(startTime, endTime, totalTime);
    totalList.setScheduledTime(scheduledTime);
    
    totalList.setEmployeeName("전체");
    totalList.setChild(employeeList);
    

    return totalList;
  }

  // (오른쪽) 담당자 세부 작업목록 가져오기
  public List<InspectionLabelingView> getListByEmployeeName(InspectionLabeling inspectionLabeling,
      Pager pager) {
    List<InspectionLabelingView> list = new ArrayList<>();
    list.addAll(inspectionLabelingDao.searchAllDetailByEmployeeName(inspectionLabeling, pager));
    return list;
  }

  // 라벨링 페이지 현황 가져오기
  public InspectionLabelingStatus getStatus() {
    InspectionLabelingStatus total = inspectionLabelingDao.searchStatusTotal();
    InspectionLabelingStatus status = inspectionLabelingDao.searchStatus();
    status.setReceiveItem(total.getReceiveItem());
    status.setReceiveItemQuantity(total.getReceiveItemQuantity());
    return status;
  }

  // (오른쪽) 그리드 페이지용 전체 개수
  public int getTotalNum(InspectionLabeling inspectionLabeling) {
    return inspectionLabelingDao.countDetailByEmployeeName(inspectionLabeling);
  }

  // 날짜 데이터 정제 메소드
  private String setDateTime(String startTime, String endTime, int totalTime) {
    if (Integer.parseInt(startTime.substring(0, 2)) < 13 && Integer.parseInt(endTime.substring(0, 2)) > 13) {
      totalTime -= 60;
    }

    String hour = String.format("%02d", totalTime / 60);
    String minute = String.format("%02d", totalTime % 60);

    String scheduledTime = startTime + " ~ " + endTime + " (" + hour + ":" + minute + ")";
    return scheduledTime;
  }
}
