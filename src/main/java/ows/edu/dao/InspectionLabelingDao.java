package ows.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ows.edu.dto.InspectionLabelingStatus;
import ows.edu.dto.InspectionLabelingView;
import ows.edu.dto.InspectionLabelingWork;

@Mapper
public interface InspectionLabelingDao {
  public List<InspectionLabelingView> searchRight(String employeeName, String searchSelected, String searchContent);
  public List<InspectionLabelingWork> searchLeftTotal();
  public List<InspectionLabelingWork> searchLeftByName();
  public List<InspectionLabelingWork> searchLeft();
  public InspectionLabelingStatus searchTotal();
  public InspectionLabelingStatus searchStatus();
}
