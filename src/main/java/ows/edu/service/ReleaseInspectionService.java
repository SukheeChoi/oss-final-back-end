package ows.edu.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import ows.edu.dto.AfterPickingFilter;
import ows.edu.dto.Box;
import ows.edu.dto.Pager;
import ows.edu.dto.ReleasePacking;

@Transactional
public interface ReleaseInspectionService {
	/**
	 * @author 최숙희
	 * @return Map<String, Object> 출고검수/패킹 진행 페이지의 현황 정보
	 */
	Map<String, Object> getSummary();
	
	/**
	 * @author 최숙희
	 * @param afterPickingFilter 필터 객체
	 * @return List<String> 출고검수/패킹 담당자 이름 목록
	 */
	List<String> getAssigneeList(AfterPickingFilter afterPickingFilter);
	/**
	 * @author 최숙희
	 * @param afterPickingFilter 필터 객체
	 * @return Map<String, Object> 출고검수/패킹 진행 목록
	 */
	Map<String, Object> getAfterPickingList(AfterPickingFilter afterPickingFilter);
	
	
	   //현주 ====================================================================================================
	   //조회에 과한 내용
	   public List<ReleasePacking> select();
	   
	   public List<ReleasePacking> selectByFilterPage(Pager pager);

	   public int count();
	   
	   public List<ReleasePacking> selectByPage(Pager pager);
	   
	   public List<ReleasePacking> selectByOrderNo(String orderNo, int index);

	   
	   //검수수량, 미출고 수량 업데이트
	   public void releaseInspectionQtyUpdate(String barCode);

	   public void unRleaseQtyUpdate(String barCode);
	   
	   //스캔
	   public List<ReleasePacking> scan(String code, String kind);
	   
	   //출고검수수량 업데이트
	   public int update(List<Box> boxArray);
	   
	   //스캔했을 때, 박스별품목정보 띄어주는 용도
	   public List<ReleasePacking> selectByReleaseCode(String releaseCode);
	   
	   //검수한 물품 수량 합산
	   public int getTotalItemQty(Long orderNo);
}
