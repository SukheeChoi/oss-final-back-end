package ows.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ows.edu.dto.InspectionLabeling;
import ows.edu.dto.InspectionLabelingStatus;
import ows.edu.dto.InspectionLabelingView;
import ows.edu.dto.InspectionLabelingWork;
import ows.edu.dto.LabelingWorkTime;
import ows.edu.dto.Pager;

@Mapper
public interface InspectionLabelingDao {    //select, insert, update, delete, count
  //(왼쪽) 전체 작업 가져오기
  public LabelingWorkTime searchAllByTotal();
  
  //(왼쪽) 담당자 기준으로 전체 작업 가져오기
  public List<LabelingWorkTime> searchAllByName();

  //(왼쪽) 담당자별 작업 업체 가져오기
  public List<InspectionLabelingWork> searchAllByEmployeeName(int labelingWorkTimeNo);
  
  //(오른쪽) 담당자 세부 작업목록 가져오기
  public List<InspectionLabelingView> searchAllDetailByEmployeeName(@Param("il") InspectionLabeling inspectionLabeling, @Param("pager") Pager pager);
  
  //(오른쪽) 카운트
  public int countDetailByEmployeeName(InspectionLabeling inspectionLabeling);
  
  //단건일때는 널 체크 잘 하기(list는 isEmpty)
  //전체 현황 가져오기
  public InspectionLabelingStatus searchStatusTotal();
  
  //세부 현황 가져오기
  public InspectionLabelingStatus searchStatus();
}
