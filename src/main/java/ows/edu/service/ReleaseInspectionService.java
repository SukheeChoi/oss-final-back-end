package ows.edu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import ows.edu.dto.Box;
import ows.edu.dto.Pager;
import ows.edu.dto.ReleaseInspection;
import ows.edu.dto.ReleasePacking;

@Transactional
public interface ReleaseInspectionService {
	
	Map<String, Object> getSummary();
	
	// 출고검수/패킹 담당자 이름 목록 조회.
	List<String> getAssigneeList(
			String shippingCategory
			, String shippingWay
			, String released
			, int orderNo
			, String clientName
			, String shippingDestination
			, String vendorName
	);
	
	int getTotalRows(
			String shippingCategory
			, String shippingWay
			, String released
			, String assignee
			, int orderNo
			, String clientName
			, String shippingDestination
			, String vendorName
			
			, int pageNo
	);

//	List<AfterPicking> getAfterPickingList(
	List<HashMap<String, String>> getAfterPickingList(
//	List<AfterPickingView> getAfterPickingList(
			String shippingCategory
			, String shippingWay
			, String released
			, String assignee
			, int orderNo
			, String clientName
			, String shippingDestination
			, String vendorName
			
//			, int pageNo
			, Pager pager
		);
	
	
	//현주 ====================================================================================================
	//조회에 과한 내용
	public List<ReleasePacking> select();
	
	public List<ReleasePacking> selectByFilterPage(Pager pager);

	public int count();
	
	public List<ReleasePacking> selectByPage(Pager pager);
	
	public List<ReleasePacking> selectByOrderNo(String orderNo, int index);

	
	//검수수량, 미출고 수량 업데이트
	public int releaseInspectionQtyUpdate(String releaseCode, String barCode);

	public int unRleaseQtyUpdate(String releaseCode, String barCode);
	
	//스캔
	public List<ReleasePacking> scan(String code, String kind);
	
	//출고검수수량 업데이트
	public int update(List<Box> boxArray);
	
	//스캔했을 때, 박스별품목정보 띄어주는 용도
	public List<ReleasePacking> selectByReleaseCode(String releaseCode);
}
