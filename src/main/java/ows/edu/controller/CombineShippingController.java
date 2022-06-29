package ows.edu.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import ows.edu.dto.CombineShipping;
import ows.edu.dto.Employee;
import ows.edu.dto.Vendor;
import ows.edu.service.CombineShippingService;

@RestController
@RequestMapping("/combineShipping")
@Log4j2
public class CombineShippingController {

	@Resource
	CombineShippingService combineShippingService;

	
	//수령 대상 업체 필터링을 위한 조회.
	//선택된 날짜(parameter로 넘김.) || (parameter X)오늘 날짜에 해당하도록 조회할 것.
	@PostMapping("/getVendorList")
	public Map<String, Object> getVendorList(@RequestParam(value="toDo", defaultValue="1") int toDo
											, @RequestParam(value="dateList", defaultValue="[]") String[] dateList) {
		Map<String, Object> map = new HashMap<>();
		List<Vendor> list = new ArrayList<>();
		
		list = combineShippingService.getVendorList(toDo, dateList);
		
		if(list.isEmpty()) {
			map.put("list", null);
		} else {
			map.put("list", list);
		}
		
		return map;
	}
	
//	'전달'탭 선택시에 표시되는 합배송 담당직원의 목록 조회.
//	당일의 '전달'사항을 할일로 가진 모든 담당자 조회.
//	수령1 + 전달0인 행들의 모든 EMP_NAME을 중복없이 정렬해서 전달.
	@PostMapping("/getAssignee")
	public Map<String, Object> getAssignee(@RequestParam(value="toDo", defaultValue="1") int toDo
											, @RequestParam(value="dateList", defaultValue="[]") String[] dateList){
		
		Map<String, Object> map = new HashMap<>();
		List<String> list = combineShippingService.getAssigneeListByDate(toDo, dateList);
//		List<Employee> list = combineShippingService.getAssigneeListByDate(dateList);
		if(list.isEmpty()) {
			map.put("list", null);
		} else {
			map.put("list", list);
		}
		log.info("map" + map);
		return map;
	}
	
//	선택된 담당자를 기준으로 수령 목록 조회.
	@PostMapping("/getReceiptList")
	public Map<String, Object> geReceiptList(@RequestParam(value="toDo", defaultValue="1") int toDo
											, @RequestParam(value="employeeId", defaultValue="") String employeeId
											, @RequestParam(value="dateList", defaultValue="[]") String[] dateList){
		log.info("getReceiptList - toDo : " + toDo);
		log.info("getReceiptList - employeeId : " + employeeId);
		log.info("getReceiptList - dateList : " + dateList);
		// 필요한 OI_NO 조회해서 List로 받아옴.
		List<String> orderItemNoList = new ArrayList<>();
		
		if(dateList != null) {
			log.info("orderItemNoList : " + orderItemNoList);
			orderItemNoList = combineShippingService.getReceiptOrderItemNoList(toDo, employeeId, dateList);
		}
		
		Map<String, Object> map = new HashMap<>();
		if(orderItemNoList.isEmpty()) {
			map.put("receiptList", null);
		} else {
			List<CombineShipping> receiptList = new ArrayList<>();
			for(String orderItemNo : orderItemNoList) {
				log.info("orderItemNo : " + orderItemNo);
				receiptList.add(combineShippingService.getAReceipt(orderItemNo));
			}
			log.info("receiptList : " + receiptList);
			map.put("receiptList", receiptList);
		}
		
		return map;
	}
//	선택된 담당자를 기준으로 전달 목록 조회.
	@PostMapping("/getDeliveryList")
	public Map<String, Object> getDeliveryList(@RequestParam(value="toDo", defaultValue="1") int toDo
											, @RequestParam(value="employeeId", defaultValue="") String employeeId
											, @RequestParam(value="dateList", defaultValue="[]") String[] dateList) {
		log.info("getDeliveryList - toDo : " + toDo);
		log.info("getDeliveryList - employeeId : " + employeeId);
		log.info("getDeliveryList - dateList : " + dateList);
		
		// 필요한 OI_NO 조회해서 List로 받아옴.
		List<String> orderItemNoList = new ArrayList<>();
		
		//날짜 필터링이 선택되지 않은 경우이므로, 당일의 정보만을 조회.
		//날짜 무관하게 처리되지 않은 목록 조회하는 것으로 로직변경 필요.
		orderItemNoList = combineShippingService.getDeliveryOrderItemNoList(toDo, employeeId, dateList);
		
		log.info("getDeliveryList - orderItemNoList : " + orderItemNoList);
		Map<String, Object> map = new HashMap<>();
		if(orderItemNoList.isEmpty()) {
			map.put("deliveryList", null);
		} else {
			List<CombineShipping> deliveryList = new ArrayList<>();
			for(String orderItemNo : orderItemNoList) {
				log.info("orderItemNo : " + orderItemNo);
				deliveryList.add(combineShippingService.getADelivery(orderItemNo));
				log.info("deliveryList : " + deliveryList);
			}
			map.put("deliveryList", deliveryList);
		}
		
		return map;
	}
	
	// 수령여부 선택된 행들의 정보를 []로 받아와서,
	// OI_NO를 기준으로
	// ITM_NAME, ITM_CODE, 
	@PutMapping("/receipt")
	public Map<String, String> updateReceipt(@RequestBody CombineShipping[] receiptListForUpdate) {
		Map<String, String> resultMap = new HashMap<>();
		log.info("combineShippingList[0] : " + receiptListForUpdate[0]);
		String result = combineShippingService.updateReceipt(receiptListForUpdate);
		resultMap.put("result", result);
		return resultMap;
	}
	
// 전달여부가 선택된 행들의 정보를 []로 받아와서,
// OI_NO를 기준으로
// CS_DLV_QTY, CS_DLV_URLS, CS_DLV_CHK update.
	@PutMapping("/delivery")
	public Map<String, String> updateDelivery(@RequestBody int[] orderItemNoList) {
//		public Map<String, String> updateDelivery(@RequestBody CombineShipping[] combineShippingList) {
		log.info("orderItemList.length : " + orderItemNoList.length);

		Map<String, String> resultMap = new HashMap<>();
		String result = combineShippingService.updateDelivery(orderItemNoList);
		resultMap.put("result", result);
		return resultMap;
	}
	
// 선택된 기간 동안안의 수령 목록 조회.
//	@PostMapping("/getReceiptListByDate")
//	public Map<String, Object> getReceiptListByDate(@RequestBody String[] dateList) {
//		Map<String, Object> map = new HashMap<>();
//		List<CombineShipping> list = combineShippingService.getReceiptListByDate(dateList);
//		map.put("list", list);
//		log.info("list : " + list);
//		return map;
//	}
	
}
