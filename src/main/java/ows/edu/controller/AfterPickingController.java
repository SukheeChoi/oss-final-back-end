package ows.edu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import ows.edu.dto.ReleaseInspection;
import ows.edu.service.ReleaseInspectionService;

@RestController
@RequestMapping("/afterPicking")
@Log4j2
public class AfterPickingController {

	@Resource
	ReleaseInspectionService releaseInspectionService;

	
	//주문건수, 피킹지시건수, 출고검수+패킹 건수, 미출고건수, 출고검수 건수, 출고검수 중에서 긴급/일반 건수
	@GetMapping("/summary")
	public Map<String, Object> getSummary() {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> summaryMap = releaseInspectionService.getSummary();
		map.put("summaryMap", summaryMap);
		
		return map;
	}

	@PostMapping("/")
	public Map<String, Object> getList(
			@RequestParam(value="shippingCategory", defaultValue="null") String shippingCategory
			, @RequestParam(value="shippingWay", defaultValue="null") String shippingWay
			, @RequestParam(value="released", defaultValue="null") String released
			, @RequestParam(value="assignee", defaultValue="null") String assignee
			, @RequestParam(value="orderNo", defaultValue="-1") int orderNo
			, @RequestParam(value="clientName", defaultValue="null") String clientName
			, @RequestParam(value="shippingDestination", defaultValue="null") String shippingDestination
			, @RequestParam(value="vendorName", defaultValue="null") String vendorName
		) {
		log.info("shippingCategory : " + shippingCategory);
		log.info("released : " + released);
		log.info("assignee : " + assignee);
		log.info("orderNo : " + orderNo);
//		log.info("shippingDestination : " + shippingDestination);
		Map<String, Object> map = new HashMap<>();
		List<ReleaseInspection> list = releaseInspectionService
				.getAfterPickingList(
					shippingCategory
					, shippingWay
					, released
					, assignee
					, orderNo
					, clientName
					, shippingDestination
					, vendorName
				);
		
		map.put("list", list);
		return map;
	}
	
}
