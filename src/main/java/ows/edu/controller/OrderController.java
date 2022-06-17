package ows.edu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import ows.edu.service.impl.OrderServiceImpl;

@RestController
@RequestMapping("/order")
@Log4j2
public class OrderController {
	@Resource
	OrderServiceImpl orderService;
	
	// 주문확인 페이지
	
	// 페이지번호를 받아와서, 해당하는 '주문확인'페이지의 결과를 출력.
	@GetMapping("/{pageNo}")
	public List<String> getOrderList(@PathVariable int pageNo) { 
		List<String> list = new ArrayList<>();
		
		// totalRows 확인.
		int totalRows = orderService.getTotalOrders();
//		Pager pager = new Pager(20, 10, pageNo, totalRows);
		
		// OrderService의 메소드에 pageNo를 파라미터로 넘겨주고
		// '주문확인' 페이지 1개 분량의 정보를 list로 받아온다.
//		list = orderService.getOrderNoList(pager);
		
		return list;
	}
}
