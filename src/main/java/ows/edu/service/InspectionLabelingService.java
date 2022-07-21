package ows.edu.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;
import ows.edu.dao.InspectionLabelingDao;
import ows.edu.dto.InspectionLabeling;
import ows.edu.dto.InspectionLabelingStatus;
import ows.edu.dto.InspectionLabelingView;
import ows.edu.dto.InspectionLabelingWork;
import ows.edu.dto.LabelingWorkTime;
import ows.edu.dto.Pager;
import ows.edu.dto.UpdateTime;

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
      // 3번째 업체별 리스트
      List<InspectionLabelingWork> workList = new ArrayList<>();
      labelingWorkTimeNo = employee.getLabelingWorkTimeNo();
      workList.addAll(inspectionLabelingDao.searchAllByLWTNo(labelingWorkTimeNo));
      for (InspectionLabelingWork labelingWork : workList) {
        
        // 업체별 예정 시간 정제
        String startTime = labelingWork.getScheduledStartTime();
        String endTime = labelingWork.getScheduledEndTime();
        int totalTime = labelingWork.getTotalWorkTime();
        String scheduledTime = setDateTime(startTime, endTime, totalTime);
        labelingWork.setScheduledTime(scheduledTime);
        labelingWork.setEmployeeName(employee.getTitle());
      }
      employee.setChildrennn(workList);
      
      // 담당자별 예정 시간 정제
      String startTime = employee.getScheduledStartTime();
      String endTime = employee.getScheduledEndTime();
      int totalTime = employee.getTotalWorkTime();
      String scheduledTime = setDateTime(startTime, endTime, totalTime);
      employee.setScheduledTime(scheduledTime);
    }

    // 1번째 전체 리스트
    LabelingWorkTime totalList = inspectionLabelingDao.searchAllByTotal();
    
    //전체 예정 시간 정제
    String startTime = totalList.getScheduledStartTime();
    String endTime = totalList.getScheduledEndTime();
    int totalTime = totalList.getTotalWorkTime();
    String scheduledTime = setDateTime(startTime, endTime, totalTime);
    totalList.setScheduledTime(scheduledTime);
    
    totalList.setTitle("전체");
    totalList.setChild(employeeList);
    

    return totalList;
  }

  // (오른쪽) 담당자 세부 작업목록 가져오기
  public Map<String, Object> getListByLWTNo(InspectionLabeling inspectionLabeling, int pageNo, int pageSize) {
    
    List<InspectionLabelingView> data = new ArrayList<>();
    
    int totalCount = inspectionLabelingDao.countDetailByLWTNO(inspectionLabeling);
    Pager pager = new Pager(pageSize, 5, totalCount, pageNo);
    
    data.addAll(inspectionLabelingDao.searchAllDetailByLWTNo(inspectionLabeling, pager));
    
    Map<String, Object> map = new HashMap<>();
    map.put("data", data);
    map.put("totalCount", totalCount);
    return map;
  }
  
  //잔업 가져오기
  public List<InspectionLabelingWork> getListByLWTNoIsNull() {
    List<InspectionLabelingWork> list = new ArrayList<>();
    list.addAll(inspectionLabelingDao.searchAllByLWTNoIsNULL());
    return list;
  }
  
  //잔업 추가하기
  @Transactional
  public String updateOvertime(UpdateTime updateTime) throws ParseException {
    log.info(updateTime);
    //날짜 형식변환
    String startTime = updateTime.getStartTime();
    String endTime = updateTime.getEndTime();

    SimpleDateFormat sdfYMD = new SimpleDateFormat("yyyy년 MM월 dd일");
    String date = sdfYMD.format(new Date());

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일HH:mm");
    Date lastStartDate = sdf.parse(date + startTime);
    Date lastEndDate = sdf.parse(date + endTime);
    
    Date testStartDate =sdf.parse("2022년 07월 27일" + startTime);
    Date testEndDate = sdf.parse("2022년 07월 27일"  + endTime);
    

    int overTimeResult = inspectionLabelingDao.updateLabelingWorkTime(updateTime.getReceiveItem(), updateTime.getReceiveQuantity(), updateTime.getLabelingWorkTimeNo());
    int testTimeResult = inspectionLabelingDao.updateInspectionLabelingWork(testStartDate, testEndDate, updateTime.getLabelingWorkTimeNo(), updateTime.getPlacingOrderNo());
//    int workTimeResult = inspectionLabelingDao.updateInspectionLabelingWork(lastStartDate, lastEndDate, updateTime.getLabelingWorkTimeNo(), updateTime.getPlacingOrderNo());
    
    String result = "fail";
    if(overTimeResult + testTimeResult == 2) {
      result = "success";
    }
    return result;
  }
  
  //작업시간 수정하기
  public String updateWorktime(UpdateTime updateTime) throws ParseException {
    //날짜 형식변환
    String startTime = updateTime.getStartTime();
    String endTime = updateTime.getEndTime();

    SimpleDateFormat sdfYMD = new SimpleDateFormat("yyyy년 MM월 dd일");
    String date = sdfYMD.format(new Date());
    log.info(startTime);
    log.info(endTime);
    log.info(date);
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일HH:mm");
//    Date lastStartDate = sdf.parse(date + startTime);
//    Date lastEndDate = sdf.parse(date + endTime);
    
    Date testStartDate = sdf.parse("2022년 07월 27일" + startTime);
    Date testEndDate = sdf.parse("2022년 07월 27일"  + endTime);
    
    log.info(testStartDate);
    log.info(testEndDate);
//    int workTimeResult = inspectionLabelingDao.updateInspectionLabelingWork(lastStartDate, lastEndDate, updateTime.getLabelingWorkTimeNo(), updateTime.getPlacingOrderNo());
    int testTimeResult = inspectionLabelingDao.updateInspectionLabelingWork(testStartDate, testEndDate, updateTime.getLabelingWorkTimeNo(), updateTime.getPlacingOrderNo());
    String result = "fail";
    if(testTimeResult == 1) {
      result = "success";
    }
    return result;
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
    return inspectionLabelingDao.countDetailByLWTNO(inspectionLabeling);
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
