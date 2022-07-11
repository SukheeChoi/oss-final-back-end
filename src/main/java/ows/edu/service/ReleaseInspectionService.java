package ows.edu.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ows.edu.dao.ReleaseInspectionViewDao;
import ows.edu.dto.Box;
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
	//조회에 과한 내용
	public List<ReleaseInspectionView> select();
	
	public List<ReleaseInspectionView> selectByFilterPage(Pager pager);

	public int count();
	
	public List<ReleaseInspectionView> selectByPage(Pager pager);
	
	public List<ReleaseInspectionView> selectByOrderNo(int orderNo);
	
	//검수수량, 미출고 수량 업데이트
	public int releaseInspectionQtyUpdate(String releaseCode);

	public int unRleaseQtyUpdate(String releaseCode);
	
	//스캔
	public ReleaseInspection scan(String code, String kind);
	
	//출고검수수량 업데이트
	public int update(List<Box> boxArray);
	
	//스캔했을 때, 박스별품목정보 띄어주는 용도
	public List<ReleaseInspectionView> selectByReleaseCode(String releaseCode);
}
