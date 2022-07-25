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

  /**
   * 전체 검품검수 및 라벨링 작업 합산 데이터 반환
   * 
   * @author 이동현
   * @return 담당자별 작업 리스트의 합산 데이터 반환
   */
  public LabelingWorkTime selectAllByTotal();
  
  /**
   * 담당자별 검품검수 및 라벨링 작업 정보 반환
   * 
   * @author 이동현
   * @return 담당자별 작업 정보 목록 반환
   */
  public List<LabelingWorkTime> selectAllByName();

  /**
   * 담당자별 검품검수 및 라벨링 작업 업체 정보 반환
   * 
   * @author 이동현
   * @param labelingWorkTimeNo 작업번호
   * @return 해당 담당자의 업체별 작업 정보 목록 반환
   */
  public List<InspectionLabelingWork> selectAllByLWTNo(int labelingWorkTimeNo);
  
  /**
   * 담당자별 검품검수 및 라벨링 세부내역 반환
   * 
   * @author 이동현
   * @param inspectionLabeling 작업번호, 검색조건, 검색내용을 포함
   * @param pager 페이저 객체
   * @return 페이지에 해당하고 조건에 맞는 세부 내역 목록 반환
   */
  public List<InspectionLabelingView> selectAllDetailByLWTNo(@Param("il") InspectionLabeling inspectionLabeling, @Param("pager") Pager pager);
  
  /**
   * 담당자별 검품검수 및 라벨링 세부내역 개수 반환
   * 
   * @author 이동현
   * @param inspectionLabeling 작업번호, 검색조건, 검색내용을 포함
   * @return 조건에 맞는 데이터 개수 반환
   */
  public int countDetailByLWTNO(InspectionLabeling inspectionLabeling);
  
  /**
   * 잔업 가져오기
   * 
   * @author 이동현
   * @return 담당자가 지정되지 않은 작업 목록 반환
   */
  public List<InspectionLabelingWork> selectAllByLWTNoIsNULL();
  
  /**
   * 담당자의 전체 작업 개수 추가하기
   * 
   * @author 이동현
   * @param receiveItem 수령폼목개수
   * @param receiveQuantity 수령수량
   * @param labelingWorkTimeNo 작업번호
   * @return 작업 개수 추가 성공여부(1 성공, 0 실패)
   */
  public int updateLabelingWorkTime(int receiveItem, int receiveQuantity, int labelingWorkTimeNo);
  
  /**
   * 예정작업시간 수정하기
   * 
   * @author 이동현
   * @param startTime 시작시간
   * @param endTime 종료시간
   * @param labelingWorkTimeNo 작업번호
   * @param placingOrderNo 발주번호
   * @return 예정시간 수정 성공여부(1 성공, 0 실패)
   */
  public int updateInspectionLabelingWork(Date startTime, Date endTime, int labelingWorkTimeNo, String placingOrderNo);
  
  /**
   * 물품 수령 현황 반환
   * 
   * @author 이동현
   * @return 물품 수령 품목/개수 반환
   */
  public InspectionLabelingStatus selectStatusTotal();
  
  //세부 현황 가져오기
  
  /**
   * 검품검수 및 라벨링 현황 반환
   * 
   * @author 이동현
   * @return 검품검수/라벨링/양품/누락/파손 품목 및 개수 반환
   */
  public InspectionLabelingStatus selectStatus();
}
