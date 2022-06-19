package ows.edu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import ows.edu.dao.InspectionLabelingWorkDao;
import ows.edu.dao.LabelingWorkTimeDao;
import ows.edu.dao.PlacingOrderDao;
import ows.edu.dao.PlacingOrderItemDao;
import ows.edu.dto.InspectionLabeling;
import ows.edu.dto.LabelingWorkTime;

@Service
@Log4j2
public class LabelingWorkTimeService {
  
  @Resource
  private PlacingOrderDao placingOrderDao;
  
  @Resource
  private PlacingOrderItemDao placingOrderItemDao;
  
  @Resource
  private LabelingWorkTimeDao labelingWorkTimeDao;
  
  @Resource
  private InspectionLabelingWorkDao inspectionLabelingWorkDao;
  
  public LabelingWorkTime get(Date labelingWorkTimeDate) {
    LabelingWorkTime lee = labelingWorkTimeDao.searchTotal(labelingWorkTimeDate);
    log.info(lee);
    return lee;
  }
  
  public List<LabelingWorkTime> getlist(Date labelingWorkTimeDate) {
    List<LabelingWorkTime> list = new ArrayList<>();
    list.addAll(labelingWorkTimeDao.searchAll(labelingWorkTimeDate));
    log.info(list);
    return list;
  }
  
  public List<InspectionLabeling> getList(String employeeId, Date recieveDate) {
    return null;
  }
}
