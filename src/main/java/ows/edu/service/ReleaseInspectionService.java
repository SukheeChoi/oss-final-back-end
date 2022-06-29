package ows.edu.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ows.edu.dao.ReleaseInspectionViewDao;
import ows.edu.dto.Pager;
import ows.edu.dto.ReleaseInspection;
import ows.edu.dto.ReleaseInspectionView;

@Transactional
public interface ReleaseInspectionService {
	
	Map<String, Object> getSummary();

	List<ReleaseInspection> getAfterPickingList(
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
