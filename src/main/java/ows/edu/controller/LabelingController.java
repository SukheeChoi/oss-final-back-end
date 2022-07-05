package ows.edu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import ows.edu.dto.InspectionLabelingStatus;
import ows.edu.dto.InspectionLabelingView;
import ows.edu.dto.LabelingWorkTime;
import ows.edu.dto.Pager;
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
    log.info("실행");
    
    InspectionLabelingStatus inspectionLabelingStatus = inspectionLabelingService.getStatus();
    
    Map<String, Object> map = new HashMap<>();
    map.put("status", inspectionLabelingStatus);
    return map;
  }
  
  //당일 트리 그리드 데이터 
  @GetMapping("/getListLeft")
  public ResponseEntity<String> getListLeft() {
    log.info(inspectionLabelingService.getLeft());
    
    return ResponseEntity
        .ok()
        .header(HttpHeaders.CONTENT_TYPE, "application/json")
        .body(inspectionLabelingService.getLeft());
  }
  
  //담당자별 검품검수 및 라벨링 내역
  @GetMapping("/getListRight")
  public Map<String, Object> getListRight(@RequestParam String employeeName
                                                  ,@RequestParam(defaultValue = "null") String searchSelected
                                                  ,@RequestParam(defaultValue = "null") String searchContent
                                                  , @RequestParam(defaultValue = "1") int pageNo
                                                  , @RequestParam(defaultValue = "5") int pageSize) {
    
    int totalRows = inspectionLabelingService.getTotalNum(employeeName, searchSelected, searchContent);
    Pager pager = new Pager(pageSize, 5, totalRows, pageNo);
    log.info(pager);
    
    List<InspectionLabelingView> list = new ArrayList<>();
    list.addAll(inspectionLabelingService.getRight(employeeName, searchSelected, searchContent, pager));
    Map<String, Object> map = new HashMap<>();
    map.put("data", list);
    map.put("totalCount", totalRows);
    log.info("map : " + map);
    return map;
  }

}
