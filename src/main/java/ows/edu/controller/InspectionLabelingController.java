package ows.edu.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
public class InspectionLabelingController {
  
  @Autowired
  private InspectionLabelingService inspectionLabelingService;
  
  /**
   * 검품검수 및 라벨링 내역 현황을 반환함
   * 
   * @author 이동현
   * @return 물품수령/검품검수/라벨링/양품/누락/파손 품목 및 수량 반환
   */
  @GetMapping("/status")
  public Map<String, Object> getStatus() {
    
    InspectionLabelingStatus inspectionLabelingStatus = inspectionLabelingService.getStatus();
    
    Map<String, Object> map = new HashMap<>();
    map.put("status", inspectionLabelingStatus);
    return map;
  }
  
  /**
   * 당일 작업 정보를 반환함
   * 
   * @author 이동현
   * @return 전체/담당자/업체 순으로 depth를 가진 작업 정보를 반환
   */
  @GetMapping("/treeList")
  public Map<String, Object> getTreeList() {

    List<LabelingWorkTime> data = new ArrayList<>();
    data.add(inspectionLabelingService.getTreeList());
    
    Map<String, Object> map = new HashMap<>();
    map.put("data", data);
    return map;
  }
  
  /**
   * 잔업 목록을 반환
   * 
   * @author 이동현
   * @return 담당자가 지정되지 않은 작업 목록을 반환
   */
  @GetMapping("/overtime")
  public Map<String, Object> getOverTime() {
    List<InspectionLabelingWork> data = new ArrayList<>();
    data.addAll(inspectionLabelingService.getListByLWTNoIsNull());
    Map<String, Object> map = new HashMap<>();
    map.put("data", data);
    return map;
  }
  
  /**
   * 담당자별 검품검수 및 라벨링 세부내역 목록 반환함
   * 
   * @author 이동현
   * @param inspectionLabeling 작업번호, 검색조건, 검색내용을 포함
   * @param pageNo 페이지 번호
   * @param pageSize 페이지당 행 수
   * @return 전체 데이터 개수와 페이지에 해당하는 세부 내역 목록 반환
   */
  @GetMapping("/workDetail")
  public Map<String, Object> getListByLWTNo(InspectionLabeling inspectionLabeling
                                                  , @RequestParam(defaultValue = "1") int pageNo
                                                  , @RequestParam(defaultValue = "5") int pageSize) {
      
    Map<String, Object> map = inspectionLabelingService.getListByLWTNo(inspectionLabeling, pageNo, pageSize);
    return map;
  }
  
  /**
   * 해당 담당자에게 작업 추가
   * 
   * @author 이동현
   * @param updateTime 수령수량, 수령품목, 작업번호, 발주번호, 시작시간, 끝시간을 포함
   * @return 성공여부 반환
   */
  @PutMapping("/overtime")
  public Map<String, String> updateOvertime(@RequestBody UpdateTime updateTime) {
	  
    String result;
    try {
      result = inspectionLabelingService.updateOvertime(updateTime);
    } catch (ParseException e) {
      result = "error";
    }
    
    Map<String, String> map = new HashMap<>();

    map.put("result", result);
    return map;
  }
  
  /**
   * 시작되지 않은 작업의 예정시간 수정
   * 
   * @author 이동현
   * @param updateTime 작업번호, 발주번호, 시작시간, 끝시간을 포함
   * @return 성공여부 반환
   */
  @PutMapping("/worktime")
  public Map<String, String> updateWorktime(@RequestBody UpdateTime updateTime) {
    
    String result = null;
    try {
      result = inspectionLabelingService.updateWorktime(updateTime);
    } catch (ParseException e) {
      result = "error";
    }
    
    Map<String, String> map = new HashMap<>();
    map.put("result", result);
    return map;
  }
  
}
