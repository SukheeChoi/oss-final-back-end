package ows.edu.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AfterPickingViewDao {
	public List<HashMap<String, String>> selectAll(
			String shippingCategory
			, String shippingWay
			, String released
			, String assignee
//			, int orderNo
			
			, String strOrderNo
			
			, String clientName
			, String shippingDestination
			, String vendorName
		);

}
