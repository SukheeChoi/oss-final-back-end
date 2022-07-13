package ows.edu.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ows.edu.dto.Pager;

@Mapper
public interface AfterPickingViewDao {

	public int selectCountAll(
			String shippingCategory
			, String shippingWay
			, String released
			, String assignee
			, int orderNo
			, String clientName
			, String shippingDestination
			, String vendorName
		);

	
	public List<HashMap<String, String>> selectAll(
//	public List<AfterPickingView> selectAll(
			String shippingCategory
			, String shippingWay
			, String released
			, String assignee
			, int orderNo
			
//			, String strOrderNo
			
			, String clientName
			, String shippingDestination
			, String vendorName
			
			, Pager pager
		);

	public List<String> selectReleaseInspectionEmployeeName(
			String shippingCategory
			, String shippingWay
			, String released
			, int orderNo
			, String clientName
			, String shippingDestination
			, String vendorName
		);

}
