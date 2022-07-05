package ows.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ows.edu.dto.InspectionLabelingStatus;
import ows.edu.dto.InspectionLabelingView;
import ows.edu.dto.InspectionLabelingWork;
import ows.edu.dto.LabelingWorkTime;
import ows.edu.dto.Pager;

@Mapper
public interface InspectionLabelingDao {
  //(왼쪽) 전체 작업 가져오기
  public List<LabelingWorkTime> searchAllLeftTotal();
  
  //(왼쪽) 담당자 기준으로 작업 가져오기
  public List<LabelingWorkTime> searchAllLeftByName();
  //insert, update, delete, count
  //(왼쪽) 담당자별 작업 가져오기
  public List<InspectionLabelingWork> searchAllLeft(int labelingWorkTimeNo);
  
  //(오른쪽) 담당자 세부 작업목록 가져오기
  public List<InspectionLabelingView> searchAllRight(String employeeName, String searchSelected, String searchContent, @Param("pager") Pager pager);
  
  //(오른쪽) 카운트
  public int countRight(String employeeName, String searchSelected, String searchContent);
  
  //단건일때는 널 체크 잘 하기(list는 isEmpty)
  //전체 현황 가져오기
  public InspectionLabelingStatus searchStatusTotal();
  
  //세부 현황 가져오기
  public InspectionLabelingStatus searchStatus();
}
