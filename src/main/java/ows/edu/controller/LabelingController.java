package ows.edu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import ows.edu.dto.InspectionLabelingStatus;
import ows.edu.dto.InspectionLabelingView;
import ows.edu.dto.InspectionLabelingWork;
import ows.edu.service.InspectionLabelingService;
import ows.edu.service.LabelingWorkTimeService;

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
  
  @GetMapping("/getListRight")
  public List<InspectionLabelingView> getListRight(@RequestParam String employeeName) {
    List<InspectionLabelingView> list = new ArrayList<>();
    list.addAll(inspectionLabelingService.getRight(employeeName));
    log.info(list);
    return list;
  }
  
  @GetMapping("/getListLeft")
  public List<InspectionLabelingWork> getListLeft() {
    List<InspectionLabelingWork> list = new ArrayList<>();
    list.addAll(inspectionLabelingService.getLeft());
    log.info(list);
    return list;
  }
  
}
