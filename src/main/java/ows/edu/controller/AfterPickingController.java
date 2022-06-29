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
import ows.edu.dto.AfterPicking;
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
//	public Map<String, Object> getList(
	public Map<String, Object> getList(
			@RequestParam(value="shippingCategory", defaultValue="") String shippingCategory
			, @RequestParam(value="shippingWay", defaultValue="") String shippingWay
			, @RequestParam(value="released", defaultValue="") String released
			, @RequestParam(value="assignee", defaultValue="") String assignee
			, @RequestParam(value="orderNo", defaultValue="-1") int orderNo
			, @RequestParam(value="clientName", defaultValue="") String clientName
			, @RequestParam(value="shippingDestination", defaultValue="") String shippingDestination
			, @RequestParam(value="vendorName", defaultValue="") String vendorName
		) {
		log.info("shippingCategory : " + shippingCategory);
		log.info("shippingWay : " + shippingWay);
		log.info("released : " + released);
		log.info("assignee : " + assignee);
		log.info("orderNo : " + orderNo);
		log.info("clientName : " + clientName);
		log.info("shippingDestination : " + shippingDestination);
		log.info("vendorName : " + vendorName);
		Map<String, Object> map = new HashMap<>();
//		List<AfterPicking> list = releaseInspectionService
		List<HashMap<String, String>> list = releaseInspectionService
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
		log.info("list : " + list);
		map.put("list", list);
		return map;
	}
	
}
