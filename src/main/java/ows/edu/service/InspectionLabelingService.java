package ows.edu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ows.edu.dao.InspectionLabelingDao;
import ows.edu.dto.InspectionLabelingStatus;
import ows.edu.dto.InspectionLabelingView;
import ows.edu.dto.InspectionLabelingWork;

@Service
public class InspectionLabelingService {

  @Autowired
  private InspectionLabelingDao inspectionLabelingDao;
  
  public List<InspectionLabelingView> getRight(String employeeName, String searchSelected, String searchContent) {
    List<InspectionLabelingView> list = new ArrayList<>();
    list.addAll(inspectionLabelingDao.searchRight(employeeName, searchSelected, searchContent));
    return list;
  }
  
  public List<InspectionLabelingWork> getLeft() {
    List<InspectionLabelingWork> list = new ArrayList<>();
    list.addAll(inspectionLabelingDao.searchLeft());
    return list;
  }
  
  public InspectionLabelingStatus getStatus() {
    InspectionLabelingStatus total = inspectionLabelingDao.searchTotal();
    InspectionLabelingStatus status = inspectionLabelingDao.searchStatus();
    status.setReceiveItem(total.getReceiveItem());
    status.setReceiveItemQuantity(total.getReceiveItemQuantity());
    return status;
  }
}
