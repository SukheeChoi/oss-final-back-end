package ows.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ows.edu.dto.InspectionLabelingStatus;
import ows.edu.dto.InspectionLabelingView;
import ows.edu.dto.InspectionLabelingWork;
import ows.edu.dto.LabelingWorkTime;

@Mapper
public interface InspectionLabelingDao {
  //(왼쪽) 전체 현황 가져오기
  public List<LabelingWorkTime> searchLeftTotal();
  
  //(왼쪽) 담당자 기준으로 현황 가져오기
  public List<LabelingWorkTime> searchLeftByName();
  
  //(왼쪽) 담당자별 작업 가져오기
  public List<InspectionLabelingWork> searchLeft(int labelingWorkTimeNo);
  
  //(오른쪽) 담당자 세부 작업목록 가져오기
  public List<InspectionLabelingView> searchRight(String employeeName, String searchSelected, String searchContent);
  
  //전체 현황 가져오기
  public InspectionLabelingStatus searchStatusTotal();
  
  //세부 현황 가져오기
  public InspectionLabelingStatus searchStatus();
}
