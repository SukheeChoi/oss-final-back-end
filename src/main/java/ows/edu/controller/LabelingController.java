package ows.edu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ows.edu.dto.LabelingWorkTime;
import ows.edu.service.LabelingWorkTimeService;

@RestController
public class LabelingController {

  @Autowired
  private LabelingWorkTimeService labelingWorkTimeService;
  
  @GetMapping("/get")
  public LabelingWorkTime get() {
    LabelingWorkTime list = labelingWorkTimeService.get(new Date());
    return list;
  }
  
  @GetMapping("/getlist")
  public List<LabelingWorkTime> getList() {
    List<LabelingWorkTime> list = new ArrayList<>();
    list.addAll(labelingWorkTimeService.getlist(new Date()));
    return list;
  }
}
