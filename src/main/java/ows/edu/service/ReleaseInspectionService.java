package ows.edu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import ows.edu.dto.Pager;
import ows.edu.dto.ReleaseInspectionView;

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

//	List<AfterPicking> getAfterPickingList(
	List<HashMap<String, String>> getAfterPickingList(
			String shippingCategory
			, String shippingWay
			, String released
			, String assignee
			, int orderNo
			, String clientName
			, String shippingDestination
			, String vendorName
		);
	
	//현주 ====================================================================================================
	public List<ReleaseInspectionView> select();
	
	public List<ReleaseInspectionView> selectByFilterPage(Pager pager);

	public int count();
	
	public List<ReleaseInspectionView> selectByPage(Pager pager);
	
	public List<ReleaseInspectionView> selectByOrderNo(int orderNo);

}
