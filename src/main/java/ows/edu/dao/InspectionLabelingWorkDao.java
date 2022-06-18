package ows.edu.dao;

import java.util.Date;
import java.util.List;

import ows.edu.dto.InspectionLabelingWork;

public interface InspectionLabelingWorkDao {
  public List<InspectionLabelingWork> searchbyId(Date receiveMonthDay, String employeeId);  //담당자별 당일 할일 조회
}
