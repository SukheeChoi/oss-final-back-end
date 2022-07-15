package ows.edu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import ows.edu.dto.InspectionLabeling;
import ows.edu.dto.InspectionLabelingStatus;
import ows.edu.dto.InspectionLabelingView;
import ows.edu.dto.InspectionLabelingWork;
import ows.edu.dto.LabelingWorkTime;
import ows.edu.dto.Pager;
import ows.edu.dto.UpdateTime;
import ows.edu.service.InspectionLabelingService;

@RestController
@Log4j2
@RequestMapping("/label")
public class LabelingController {
  
  @Autowired
  private InspectionLabelingService inspectionLabelingService;
  
  //현황 가져오기
  @GetMapping("/getStatus")
  public Map<String, Object> getStatus() {
    
    InspectionLabelingStatus inspectionLabelingStatus = inspectionLabelingService.getStatus();
    
    Map<String, Object> map = new HashMap<>();
    map.put("status", inspectionLabelingStatus);
    return map;
  }
  
  //당일 트리 그리드 데이터 
  @GetMapping("/getTreeList")
  public Map<String, Object> getTreeList() {

    List<LabelingWorkTime> data = new ArrayList<>();
    data.add(inspectionLabelingService.getTreeList());
    
    Map<String, Object> map = new HashMap<>();
    map.put("data", data);
    return map;
  }
  
  //잔업 가져오기
  @GetMapping("/getOverTime")
  public Map<String, Object> getOverTime() {
    List<InspectionLabelingWork> data = new ArrayList<>();
    data.addAll(inspectionLabelingService.getListByLWTNoIsNull());
    Map<String, Object> map = new HashMap<>();
    map.put("data", data);
    return map;
  }
  
  
  //담당자별 검품검수 및 라벨링 내역
  @GetMapping("/getListByLWTNo")
  public Map<String, Object> getListByLWTNo(InspectionLabeling inspectionLabeling
                                                  , @RequestParam(defaultValue = "1") int pageNo
                                                  , @RequestParam(defaultValue = "5") int pageSize) {
    
    log.info(inspectionLabeling);
    int totalCount = inspectionLabelingService.getTotalNum(inspectionLabeling);
    Pager pager = new Pager(pageSize, 5, totalCount, pageNo);
    
    List<InspectionLabelingView> data = inspectionLabelingService.getListByLWTNo(inspectionLabeling, pager);
    
    Map<String, Object> map = new HashMap<>();
    map.put("data", data);
    map.put("totalCount", totalCount);
    log.info("map : " + map);
    return map;
  }
  
  //잔업 추가하기
  @Transactional
  @PutMapping("/updateOvertime")
  public Map<String, String> updateOvertime(UpdateTime updateTime) {
    
    String result = inspectionLabelingService.updateOvertime(updateTime);
    
    Map<String, String> map = new HashMap<>();

    map.put("result", result);
    return map;
  }
  
  //작업시간 수정하기
  @PutMapping("/updateWorktime")
  public Map<String, String> updateWorktime(UpdateTime updateTime) {
    
    String result = inspectionLabelingService.updateWorktime(updateTime);
    
    Map<String, String> map = new HashMap<>();

    map.put("result", result);
    return map;
  }
  
  

}
