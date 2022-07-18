package ows.edu.dao;

import java.util.Date;
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
  //(왼쪽) 전체 작업 가져오기(1)
  public LabelingWorkTime searchAllByTotal();
  
  //(왼쪽) 담당자 기준으로 전체 작업 가져오기(2)
  public List<LabelingWorkTime> searchAllByName();

  //(왼쪽) 담당자별 작업 업체 가져오기(3)
  public List<InspectionLabelingWork> searchAllByLWTNo(int labelingWorkTimeNo);
  
  //(오른쪽) 담당자 세부 작업목록 가져오기
  public List<InspectionLabelingView> searchAllDetailByLWTNo(@Param("il") InspectionLabeling inspectionLabeling, @Param("pager") Pager pager);
  
  //(오른쪽) 카운트
  public int countDetailByLWTNO(InspectionLabeling inspectionLabeling);
  
  //잔업 가져오기
  public List<InspectionLabelingWork> searchAllByLWTNoIsNULL();
  
  //잔업 추가하기
  public int updateLabelingWorkTime(int receiveItem, int receiveQuantity, int labelingWorkTimeNo);
  
  //시간 수정하기
  public int updateInspectionLabelingWork(Date startTime, Date endTime, int labelingWorkTimeNo, String placingOrderNo);
  
  //단건일때는 널 체크 잘 하기(list는 isEmpty)
  //전체 현황 가져오기
  public InspectionLabelingStatus searchStatusTotal();
  
  //세부 현황 가져오기
  public InspectionLabelingStatus searchStatus();
}
