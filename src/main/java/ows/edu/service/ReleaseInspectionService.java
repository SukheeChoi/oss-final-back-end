package ows.edu.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import ows.edu.dto.ReleaseInspection;

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

	
}
