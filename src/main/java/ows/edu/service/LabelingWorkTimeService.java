package ows.edu.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import ows.edu.dao.LabelingWorkTimeDao;
import ows.edu.dto.LabelingWorkTime;

@Service
public class LabelingWorkTimeService {
  
  @Resource
  private LabelingWorkTimeDao labelingWorkTimeDao;
  
  public LabelingWorkTime getLists(Date labelingWorkTimeDate) {
    LabelingWorkTime lee = labelingWorkTimeDao.search(labelingWorkTimeDate);
    return lee;
  }
}
