package ows.edu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import ows.edu.service.OrderService;

@RestController
@RequestMapping("/order")
@Log4j2
public class OrderController {
	@Resource
	OrderService orderService;
	
	// 주문확인 페이지
	
	// 페이지네이션한 주문확인 페이지의 주문번호 리스트.
	@GetMapping("/{pageNo}")
	public List<String> getOrderNoList(@PathVariable int pageNo) {
		List<String> list = new ArrayList<>();
		// OrderService의 메소드에 pageNO를 파라미터로 넘겨주고
		// 주문번호 리스트를 받아온다.
		list = orderService.getOrderNoList(pageNo);
//		list.add("주문번호~");
		return null;
	}
	
	
	// 주문번호를 받아와서, 해당하는 '주문확인'페이지의 결과를 출력.
	@GetMapping("/")
	public Map<String, Object> getOrderList(@RequestParam int orderNo) { 
		
		return null;
	}
}
