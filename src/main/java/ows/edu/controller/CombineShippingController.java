package ows.edu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import ows.edu.dto.CombineShipping;
import ows.edu.service.CombineShippingService;

@RestController
@RequestMapping("/combineShipping")
@Log4j2
public class CombineShippingController {

	@Resource
	CombineShippingService combineShippingService;
//	선택된 담당자를 기준으로 수령 목록 조회.
	@GetMapping("/getReceiptList")
	public Map<String, Object> getCombineShippingReceiptList(@RequestParam(value="employeeId") String employeeId){
		// 필요한 OI_NO 조회해서 List로 받아옴.
		List<String> orderItemNoList = new ArrayList<>();
		orderItemNoList = combineShippingService.getReceiptOrderItemNoList(employeeId);
		log.info("orderItemNoList : " + orderItemNoList);
		//
		List<CombineShipping> receiptList = new ArrayList<>();
		for(String orderItemNo : orderItemNoList) {
			log.info("orderItemNo : " + orderItemNo);
			receiptList.add(combineShippingService.getAReceipt(orderItemNo));
		}
		log.info("receiptList : " + receiptList);
		Map<String, Object> map = new HashMap<>();
		map.put("receiptList", receiptList);
		return map;
	}
//	선택된 담당자를 기준으로 전달 목록 조회.
	@GetMapping("/getDeliveryList")
	public Map<String, Object> getCombineShippingDeliveryList(@RequestParam(value="employeeId") String employeeId){
		// 필요한 OI_NO 조회해서 List로 받아옴.
		List<String> orderItemNoList = new ArrayList<>();
		orderItemNoList = combineShippingService.getDeliveryOrderItemNoList(employeeId);
		log.info("orderItemNoList : " + orderItemNoList);
		// 
		List<CombineShipping> deliveryList = new ArrayList<>();
		for(String orderItemNo : orderItemNoList) {
			log.info("orderItemNo : " + orderItemNo);
			deliveryList.add(combineShippingService.getADelivery(orderItemNo));
		}
		log.info("deliveryList : " + deliveryList);
		Map<String, Object> map = new HashMap<>();
		map.put("deliveryList", deliveryList);
		return map;
	}
	
	
}
