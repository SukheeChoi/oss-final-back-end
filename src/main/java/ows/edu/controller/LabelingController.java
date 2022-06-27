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
import ows.edu.service.InspectionLabelingService;

@RestController
@Log4j2
@RequestMapping("/label")
public class LabelingController {
  
  @Autowired
  private InspectionLabelingService inspectionLabelingService;
  
  @GetMapping("/getStatus")
  public Map<String, Object> getStatus() {
    log.info("실행");
    
    InspectionLabelingStatus inspectionLabelingStatus = inspectionLabelingService.getStatus();
    
    Map<String, Object> map = new HashMap<>();
    map.put("status", inspectionLabelingStatus);
    return map;
  }
  
  @GetMapping("/getListLeft")
  public ResponseEntity<String> getListLeft() {
    log.info(inspectionLabelingService.getLeft());
    
    return ResponseEntity
        .ok()
        .header(HttpHeaders.CONTENT_TYPE, "application/json")
        .body(inspectionLabelingService.getLeft());
  }
  
  @GetMapping("/getListRight")
  public List<InspectionLabelingView> getListRight(@RequestParam String employeeName
                                                  ,@RequestParam(defaultValue = "null") String searchSelected
                                                  ,@RequestParam(defaultValue = "null") String searchContent) {
    List<InspectionLabelingView> list = new ArrayList<>();
    list.addAll(inspectionLabelingService.getRight(employeeName, searchSelected, searchContent));
    log.info(searchContent);
    log.info(list);
    return list;
  }

}
