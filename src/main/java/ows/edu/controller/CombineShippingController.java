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

	/**
	 * 필터링 정보에 해당하는 수령 대상 업체 조회
	 * 
	 * @author 최숙희
	 * @param toDo     할일 상태값(1=할일, 0=한일)
	 * @param dateList [시작일, 종료일] 선택된 날짜가 없으면 당일로 조회
	 * @return 협력사 목록
	 */
	@PostMapping("/vendorList")
	public Map<String, Object> getVendorList(@RequestParam(value = "toDo", defaultValue = "1") int toDo,
			@RequestParam(value = "dateList", defaultValue = "[]") String[] dateList) {
		log.info("vendorList");

		Map<String, Object> map = new HashMap<>();

		List<Vendor> list = new ArrayList<>();
		list = combineShippingService.getVendorList(toDo, dateList);

		if (list.isEmpty()) {
			map.put("list", null);
		} else {
			map.put("list", list);
		}

		return map;
	}

	/**
	 * 필터링 정보에 해당하는 전달 담당자 조회
	 * 
	 * @author 최숙희
	 * @param toDo     할일 상태값(1=할일, 0=한일)
	 * @param dateList [시작일, 종료일] 선택된 날짜가 없으면 당일로 조회
	 * @return 합배송 담당직원의 목록
	 */
	@PostMapping("/assigneeList")
	public Map<String, Object> getAssigneeList(@RequestParam(value = "toDo", defaultValue = "1") int toDo,
			@RequestParam(value = "dateList", defaultValue = "[]") String[] dateList) {

		Map<String, Object> map = new HashMap<>();
		List<Employee> list = combineShippingService.getAssigneeList(toDo, dateList);
		if (list.isEmpty()) {
			map.put("list", null);
		} else {
			map.put("list", list);
		}
		return map;
	}

	/**
	 * @author 최숙희
	 * @param toDo        할일 상태값(1=할일, 0=한일)
	 * @param vendorId    선택된 협력사PK
	 * @param dateList    [시작일, 종료일] 선택된 날짜가 없으면 당일로 조회
	 * @param pageNo      선택된 페이지 번호
	 * @param rowsPerPage 그리드에 표시될 행 수
	 * @return 필터링된 수령 목록
	 */
	@PostMapping("/receiptList")
	public Map<String, Object> geReceiptList(@RequestParam(value = "toDo", defaultValue = "1") int toDo,
			@RequestParam(value = "vendorId", defaultValue = "전체") String vendorId,
			@RequestParam(value = "dateList", defaultValue = "[]") String[] dateList,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "perPage", defaultValue = "40") int rowsPerPage) {
		
		log.info("receiptList");
		Map<String, Object> map = new HashMap<>();
		map = combineShippingService.getReceiptList(toDo, vendorId, dateList, pageNo, rowsPerPage);

		return map;
	}

	/**
	 * @author 최숙희
	 * @param toDo        할일 상태값(1=할일, 0=한일)
	 * @param employeeId  선택된 전달 담당자 PK
	 * @param dateList    [시작일, 종료일] 선택된 날짜가 없으면 당일로 조회
	 * @param pageNo      선택된 페이지 번호
	 * @param rowsPerPage 그리드에 표시할 행 수
	 * @return 필터링 된 전달 목록
	 */
	@PostMapping("/deliveryList")
	public Map<String, Object> getDeliveryList(@RequestParam(value = "toDo", defaultValue = "1") int toDo,
			@RequestParam(value = "employeeId", defaultValue = "전체") String employeeId,
			@RequestParam(value = "dateList", defaultValue = "[]") String[] dateList,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "perPage", defaultValue = "40") int rowsPerPage) {

		Map<String, Object> map = combineShippingService.getDeliveryList(toDo, employeeId, dateList, pageNo,
				rowsPerPage);

		return map;
	}

	/**
	 * @author 최숙희
	 * @param receiptListForUpdate { PK(OI_NO), 미출고 값 }
	 * @return 업데이트 성공여부
	 */
	@PutMapping("/receipt")
	public Map<String, String> updateReceipt(@RequestBody CombineShippingPartner[] receiptListForUpdate) {
		log.info("receiptListForUpdate : " + receiptListForUpdate);
		
		Map<String, String> resultMap = new HashMap<>();
		String result = combineShippingService.updateReceipt(receiptListForUpdate);
		resultMap.put("result", result);

		return resultMap;
	}

	/**
	 * @author 최숙희
	 * @param orderItemNoList [ PK(OI_NO) ]
	 * @return 업데이트 성공여부
	 */
	@PutMapping("/delivery")
	public Map<String, String> updateDelivery(@RequestBody CombineShippingPartner[] deliveryListForUpdate) {
		log.info("deliveryListForUpdate.length : " + deliveryListForUpdate.length);

		Map<String, String> resultMap = new HashMap<>();
		String result = combineShippingService.updateDelivery(deliveryListForUpdate);
		resultMap.put("result", result);
		return resultMap;
	}

}
