package ows.edu.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ows.edu.dto.AfterPickingFilter;
import ows.edu.dto.Pager;

@Mapper
public interface AfterPickingViewDao {

	public int selectCountAll(
//			String shippingCategory
//			, String shippingWay
//			, String released
//			, String assignee
//			, int orderNo
//			, String clientName
//			, String shippingDestination
//			, String vendorName
			AfterPickingFilter afterPickingFilter
		);

	
	public List<HashMap<String, String>> selectAll(
//			String shippingCategory
//			, String shippingWay
//			, String released
//			, String assignee
//			, int orderNo
//			, String clientName
//			, String shippingDestination
//			, String vendorName
			@Param("afterPickingFilter") AfterPickingFilter afterPickingFilter
			, @Param("pager") Pager pager
		);

	public List<String> selectReleaseInspectionEmployeeName(
//			String shippingCategory
//			, String shippingWay
//			, String released
//			, int orderNo
//			, String clientName
//			, String shippingDestination
//			, String vendorName
			AfterPickingFilter afterPickingFilter
		);

}
