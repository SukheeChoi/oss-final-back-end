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
	@GetMapping("/getVendorList")
	public Map<String, Object> getVendorList(@RequestParam(value="dateList", defaultValue="[]") String[] dateList) {
		Map<String, Object> map = new HashMap<>();
		List<Vendor> list = new ArrayList<>();
		log.info("dateList[0] : " + dateList[0]);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strNowDate = simpleDateFormat.format(new Date()); 
		log.info("strNowDate : " + strNowDate);
		if(dateList != null) {
			if(dateList.length == 1) {
				String[] strNowDateList = new String[2];
				strNowDateList[0] = strNowDate;
				strNowDateList[1] = strNowDate;
				list = combineShippingService.getVendorList(strNowDateList);
			} else {
				list = combineShippingService.getVendorList(dateList);
			}
		}
				
		map.put("list", list);
		
		return map;
	}
	
//	'전달'탭 선택시에 표시되는 합배송 담당직원의 목록 조회.
//	당일의 '전달'사항을 할일로 가진 모든 담당자 조회.
//	수령1 + 전달0인 행들의 모든 EMP_ID기준으로 중복없이 정렬해서,EMP_ID와 EMP_NAME 전달.
	@GetMapping("/getAssignee")
	public Map<String, Object> getAssignee() {
		Map<String, Object> map = new HashMap<>();
		List<Employee> list = combineShippingService.getAssigneeListByDate();
		log.info("list : " + list);
		map.put("list", list);
		return map;
	}
	
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
	
// 전달여부가 선택된 행들의 정보를 []로 받아와서,
// OI_NO를 기준으로
// CS_DLV_QTY, CS_DLV_URLS, CS_DLV_CHK update.
	@PostMapping("/updateDelivery")
	public Map<String, String> updateDelivery(@RequestBody CombineShipping[] combineShipping) {
		Map<String, String> resultMap = new HashMap<>();
		String result = combineShippingService.updateDelivery(combineShipping);
		resultMap.put("result", result);
		return resultMap;
	}
	
// 선택된 기간 동안안의 수령 목록 조회.
	@PostMapping("/getReceiptListByDate")
	public Map<String, Object> getReceiptListByDate(@RequestBody String[] dateList) {
		Map<String, Object> map = new HashMap<>();
		List<CombineShipping> list = combineShippingService.getReceiptListByDate(dateList);
		map.put("list", list);
		log.info("list : " + list);
		return map;
	}
	
}
