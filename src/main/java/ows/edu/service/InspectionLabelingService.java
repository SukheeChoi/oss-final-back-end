package ows.edu.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import ows.edu.dao.InspectionLabelingDao;
import ows.edu.dto.InspectionLabelingStatus;
import ows.edu.dto.InspectionLabelingView;
import ows.edu.dto.InspectionLabelingWork;
import ows.edu.dto.LabelingWorkTime;

@Service
@Log4j2
public class InspectionLabelingService {

  @Autowired
  private InspectionLabelingDao inspectionLabelingDao;
  

  //(왼쪽) 트리 그리드 데이터 합치는 곳
  public String getLeft() {
    JSONObject jsonObject = new JSONObject();

    //2번째 담당자별 리스트
    List<LabelingWorkTime> employeeList = new ArrayList<>();
    employeeList.addAll(inspectionLabelingDao.searchLeftByName());
    log.info(employeeList);
    
    //3번째 업체별 리스트

    int labelingWorkTimeNo = 0;
    
    for(LabelingWorkTime employee : employeeList) {
      List<InspectionLabelingWork> workList = new ArrayList<>();
      labelingWorkTimeNo = employee.getLabelingWorkTimeNo();
      workList.addAll(inspectionLabelingDao.searchLeft(labelingWorkTimeNo));
      employee.setChildrennn(workList);
    }
    
    log.info(employeeList);
    //1번째 전체 리스트
    List<LabelingWorkTime> totalList = new ArrayList<>();
    totalList = inspectionLabelingDao.searchLeftTotal();
    totalList.get(0).setEmployeeName("전체");
    totalList.get(0).setChild(employeeList);
    jsonObject.put("item", totalList);

    return jsonObject.toString();
  }
  
  
  //(오른쪽) 담당자 세부 작업목록 가져오기
  public List<InspectionLabelingView> getRight(String employeeName, String searchSelected, String searchContent) {
    List<InspectionLabelingView> list = new ArrayList<>();
    list.addAll(inspectionLabelingDao.searchRight(employeeName, searchSelected, searchContent));
    return list;
  }
  
  //현황 가져오기
  public InspectionLabelingStatus getStatus() {
    InspectionLabelingStatus total = inspectionLabelingDao.searchStatusTotal();
    InspectionLabelingStatus status = inspectionLabelingDao.searchStatus();
    status.setReceiveItem(total.getReceiveItem());
    status.setReceiveItemQuantity(total.getReceiveItemQuantity());
    return status;
  }
}
