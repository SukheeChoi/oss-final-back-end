package ows.edu.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ows.edu.dto.OrderFilter;
import ows.edu.dto.OrderStatus;

import ows.edu.service.OrderViewService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderViewService orderViewService;

	/**
	 * 주문확인 현황을 반환함
	 * 
	 * @author 이동현
	 * @return 전체/오스템/협력사합배송/협력사직배송/미출고 내역을 반환
	 */
	@GetMapping("/status")
	public OrderStatus getStatus() {
		OrderStatus orderStatus = orderViewService.getStatus();

		Map<String, Object> map = new HashMap<>();
		map.put("status", orderStatus);
		return orderStatus;
	}

	/**
	 * 필터링 조건에 맞는 주문확인 목록을 반환함
	 * 
	 * @author 이동현
	 * @param orderFilter 회사/배송구분/미출고/검색조건/검색내용을 포함
	 * @param pageNo      페이지 번호
	 * @param pageSize    페이지당 행 수
	 * @return 전체 데이터 개수와 페이지에 해당하는 주문확인 목록 반환
	 */
	@GetMapping("/orderList")
	public Map<String, Object> getfilterList(OrderFilter orderfilter, @RequestParam(defaultValue = "1") int pageNo,
			@RequestParam(defaultValue = "16") int pageSize) {

		Map<String, Object> map = orderViewService.getListByFilter(orderfilter, pageNo, pageSize);

		return map;
	}
}
