package ows.edu.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
  
  /**
   * 트리 그리드에 바인딩 할 정제된 작업 데이터 반환
   * 
   * @author 이동현
   * @return 2단계의 Depth를 가진 트리 그리드 작업 데이터 반환
   */
  public LabelingWorkTime getTreeList() {

    int labelingWorkTimeNo = 0;
    
    // 2번째 담당자별 리스트
    List<LabelingWorkTime> employeeList = new ArrayList<>();
    employeeList.addAll(inspectionLabelingDao.selectAllByName());
    
    //담당자별 작업에 해당하는 업체 작업 가져오기
    for (LabelingWorkTime employee : employeeList) {
      // 3번째 업체별 리스트
      List<InspectionLabelingWork> workList = new ArrayList<>();
      labelingWorkTimeNo = employee.getLabelingWorkTimeNo();
      workList.addAll(inspectionLabelingDao.selectAllByLWTNo(labelingWorkTimeNo));
      
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
    LabelingWorkTime totalList = inspectionLabelingDao.selectAllByTotal();
    
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

  /**
   * 담당자별 검품검수 및 라벨링 세부내역 반환
   * 
   * @author 이동현
   * @param inspectionLabeling 작업목록
   * @param pageNo 페이지 번호
   * @param pageSize 페이지당 행 수
   * @return 페이지에 해당하고 조건에 맞는 세부 내역 목록 반환
   */
  public Map<String, Object> getListByLWTNo(InspectionLabeling inspectionLabeling, int pageNo, int pageSize) {
    
    List<InspectionLabelingView> data = new ArrayList<>();
    
    int totalCount = inspectionLabelingDao.countDetailByLWTNO(inspectionLabeling);
    Pager pager = new Pager(pageSize, 5, totalCount, pageNo);
    
    data.addAll(inspectionLabelingDao.selectAllDetailByLWTNo(inspectionLabeling, pager));
    
    Map<String, Object> map = new HashMap<>();
    map.put("data", data);
    map.put("totalCount", totalCount);
    return map;
  }
  
  /**
   * 잔업 가져오기
   * 
   * @author 이동현
   * @return 담당자가 지정되지 않은 작업 목록 반환
   */
  public List<InspectionLabelingWork> getListByLWTNoIsNull() {
    List<InspectionLabelingWork> list = new ArrayList<>();
    list.addAll(inspectionLabelingDao.selectAllByLWTNoIsNULL());
    return list;
  }
  
  /**
   * 해당 담당자에게 작업 추가하기
   * 한 가지 작업이라도 실패하면 rollback
   * 
   * @author 이동현
   * @param updateTime
   * @return 성공여부 반환(시간 수정 및 작업 개수 추가, 2가지 모두 성공 시 success 반환)
   * @throws ParseException
   */
  @Transactional
  public String updateOvertime(UpdateTime updateTime) throws ParseException {
	  
	//담당자의 당일 작업 개수 추가
	int overTimeResult = inspectionLabelingDao.updateLabelingWorkTime(updateTime.getReceiveItem(), updateTime.getReceiveQuantity(), updateTime.getLabelingWorkTimeNo());

    //날짜 형식변환
    String startTime = updateTime.getStartTime();
    String endTime = updateTime.getEndTime();

    SimpleDateFormat sdfYMD = new SimpleDateFormat("yyyy년 MM월 dd일");
    String date = sdfYMD.format(new Date());

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일HH:mm");
    Date lastStartDate = sdf.parse(date + startTime);
    Date lastEndDate = sdf.parse(date + endTime);
//  int workTimeResult = inspectionLabelingDao.updateInspectionLabelingWork(lastStartDate, lastEndDate, updateTime.getLabelingWorkTimeNo(), updateTime.getPlacingOrderNo());
    
    //시연용 테스트 코드
    Date testStartDate =sdf.parse("2022년 07월 27일" + startTime);
    Date testEndDate = sdf.parse("2022년 07월 27일"  + endTime);
    int testTimeResult = inspectionLabelingDao.updateInspectionLabelingWork(testStartDate, testEndDate, updateTime.getLabelingWorkTimeNo(), updateTime.getPlacingOrderNo());

    String result = "fail";
    if(overTimeResult + testTimeResult == 2) {
      result = "success";
    }
    return result;
  }
  
  /**
   * 해당 작업의 예정시간 수정하기
   * 
   * @author 이동현
   * @param updateTime
   * @return 성공여부 반환
   * @throws ParseException
   */
  public String updateWorktime(UpdateTime updateTime) throws ParseException {
    //날짜 형식변환
    String startTime = updateTime.getStartTime();
    String endTime = updateTime.getEndTime();

    SimpleDateFormat sdfYMD = new SimpleDateFormat("yyyy년 MM월 dd일");
    String date = sdfYMD.format(new Date());
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일HH:mm");
    Date lastStartDate = sdf.parse(date + startTime);
    Date lastEndDate = sdf.parse(date + endTime);
//  int workTimeResult = inspectionLabelingDao.updateInspectionLabelingWork(lastStartDate, lastEndDate, updateTime.getLabelingWorkTimeNo(), updateTime.getPlacingOrderNo());
    
    //시연용 테스트 코드
    Date testStartDate = sdf.parse("2022년 07월 27일" + startTime);
    Date testEndDate = sdf.parse("2022년 07월 27일"  + endTime);
    int testTimeResult = inspectionLabelingDao.updateInspectionLabelingWork(testStartDate, testEndDate, updateTime.getLabelingWorkTimeNo(), updateTime.getPlacingOrderNo());
    
    String result = "fail";
    if(testTimeResult == 1) {
      result = "success";
    }
    return result;
  }
  
  /**
   * 검품검수 및 라벨링 현황 반환
   * 
   * @author 이동현
   * @return 물품수령/검품검수/라벨링/양품/누락/파손 품목 및 수량 반환
   */
  public InspectionLabelingStatus getStatus() {
    InspectionLabelingStatus total = inspectionLabelingDao.selectStatusTotal();
    InspectionLabelingStatus status = inspectionLabelingDao.selectStatus();
    status.setReceiveItem(total.getReceiveItem());
    status.setReceiveItemQuantity(total.getReceiveItemQuantity());
    return status;
  }

  /**
   * 담당자별 검품검수 및 라벨링 세부내역 개수 반환
   * 
   * @author 이동현
   * @param inspectionLabeling
   * @return 조건에 맞는 데이터 개수 반환
   */
  public int getTotalNum(InspectionLabeling inspectionLabeling) {
    return inspectionLabelingDao.countDetailByLWTNO(inspectionLabeling);
  }

  /**
   * 날짜 데이터 정제 메소드
   * 
   * @author 이동현
   * @param startTime 시작시간
   * @param endTime 종료시간
   * @param totalTime
   * @return 점심시간을 1시간을 제외하고 원하는 날짜데이터 포맷으로 변환 후 데이터 반환
   */
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
