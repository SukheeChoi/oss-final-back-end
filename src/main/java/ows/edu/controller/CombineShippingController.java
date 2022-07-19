package ows.edu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import ows.edu.dto.CombineShippingPartner;
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
	@PostMapping("/vendorList")
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
	@PostMapping("/assigneeList")
	public Map<String, Object> getAssigneeList(@RequestParam(value="toDo", defaultValue="1") int toDo
											, @RequestParam(value="dateList", defaultValue="[]") String[] dateList){
		
		Map<String, Object> map = new HashMap<>();
		List<Employee> list = combineShippingService.getAssigneeListByDate(toDo, dateList);
		if(list.isEmpty()) {
			map.put("list", null);
		} else {
			map.put("list", list);
		}
		log.info("map" + map);
		return map;
	}
	
//	선택된 담당자를 기준으로 수령 목록 조회.
	@PostMapping("/receiptList")
	public Map<String, Object> geReceiptList(@RequestParam(value="toDo", defaultValue="1") int toDo
//											, @RequestParam(value="vendorName", defaultValue="전체") String vendorName
											, @RequestParam(value="vendorId", defaultValue="전체") String vendorId
											, @RequestParam(value="dateList", defaultValue="[]") String[] dateList
											, @RequestParam(value="pageNo", defaultValue="1") int pageNo
											, @RequestParam(value="perPage", defaultValue="40") int rowsPerPage){
		log.info("getReceiptList - toDo : " + toDo);
		log.info("getReceiptList - vendorId : " + vendorId);
		log.info("getReceiptList - dateList : " + dateList);
		log.info("getReceiptList - pageNo : " + pageNo);
		log.info("getReceiptList - rowsPerPage : " + rowsPerPage);
		// 필요한 OI_NO 조회해서 List로 받아옴.
		Map<String, Object> map = new HashMap<>();
		
		map = combineShippingService
				.getReceiptList(
					toDo
					, vendorId
					, dateList
					, pageNo
					, rowsPerPage
				);
		return map;
	}
//	선택된 담당자를 기준으로 전달 목록 조회.
	@PostMapping("/deliveryList")
	public Map<String, Object> getDeliveryList(@RequestParam(value="toDo", defaultValue="1") int toDo
											, @RequestParam(value="employeeId", defaultValue="전체") String employeeId
//											, @RequestParam(value="employeeName", defaultValue="전체") String employeeName
											, @RequestParam(value="dateList", defaultValue="[]") String[] dateList
											, @RequestParam(value="pageNo", defaultValue="1") int pageNo
											, @RequestParam(value="perPage", defaultValue="40") int rowsPerPage
			) {
		log.info("getDeliveryList - toDo : " + toDo);
		log.info("getDeliveryList - employeeId : " + employeeId);
		log.info("getDeliveryList - dateList : " + dateList);
		log.info("getDeliveryList - pageNo : " + pageNo);

		Map<String, Object> map = combineShippingService
									.getDeliveryList(
											toDo, employeeId, dateList
											, pageNo
											, rowsPerPage
									);
		
		return map;
	}
	
	// 수령여부 선택된 행들의 정보를 []로 받아와서,
	// OI_NO를 기준으로
	// ITM_NAME, ITM_CODE, 
	@PutMapping("/receipt")
	public Map<String, String> updateReceipt(@RequestBody CombineShippingPartner[] receiptListForUpdate) {
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
	
	
}
