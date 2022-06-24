package ows.edu.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import ows.edu.dto.OrderFilter;
import ows.edu.dto.OrderStatus;
import ows.edu.dto.OrderView;
import ows.edu.dto.Pager;
import ows.edu.service.OrderViewService;

@RestController
@CrossOrigin(origins="*", allowedHeaders = "*")
@RequestMapping("/order")
@Log4j2
public class OrderController {
//	@Resource
//	OrderServiceImpl orderService;
//	
//	// 주문확인 페이지
//	
//	// 페이지번호를 받아와서, 해당하는 '주문확인'페이지의 결과를 출력.
//	@GetMapping("/{pageNo}")
//	public List<String> getOrderList(@PathVariable int pageNo) { 
//		List<String> list = new ArrayList<>();
//		
//		// totalRows 확인.
//		int totalRows = orderService.getTotalOrders();
////		Pager pager = new Pager(20, 10, pageNo, totalRows);
//		
//		// OrderService의 메소드에 pageNo를 파라미터로 넘겨주고
//		// '주문확인' 페이지 1개 분량의 정보를 list로 받아온다.
////		list = orderService.getOrderNoList(pager);
//		
//		return list;
//	}
  @Autowired
  private OrderViewService orderViewService;
  
  @GetMapping("/orderStatus")
  public OrderStatus getStatus() {
    log.info("실행");
    
    OrderStatus orderStatus = orderViewService.getStatus();
    
    Map<String, Object> map = new HashMap<>();
    map.put("list", orderStatus);
    return orderStatus;
  }
  
  @GetMapping("/orderview")
  public Map<String, Object> getList(@RequestParam(defaultValue = "1") int pageNo) {
    log.info("실행");
    int totalRows = orderViewService.getTotalNum();
    Pager pager = new Pager(5, 5, totalRows, pageNo);
//    List<OrderView> list = orderViewService.getList(pager);
    List<OrderView> list = orderViewService.getList();
    Map<String, Object> map = new HashMap<>();
    map.put("list", list);
    map.put("pager", pager);
    return map;
  }
  
  @GetMapping("/orderfilter")
  public Map<String, Object> filterList(@RequestParam(value="company") String[] company
                         , @RequestParam(value="shippingway", defaultValue = "null") String[] shippingway
                         , @RequestParam(value="unreleased", defaultValue = "null") String[] unreleased
                         , @RequestParam(value="searchSelected", defaultValue = "null") String searchSelected
                         , @RequestParam(value="searchContent", defaultValue = "null") String searchContent
                         , @RequestParam(defaultValue = "1") int pageNo) {
    
    OrderFilter orderfilter = new OrderFilter();
    orderfilter.setCompany(company);
    orderfilter.setShippingway(shippingway);
    orderfilter.setUnreleased(unreleased);
    orderfilter.setSearchSelected(searchSelected);
    orderfilter.setSearchContent(searchContent);
    int totalRows = orderViewService.getTotalNum();
    Pager pager = new Pager(5, 5, totalRows, pageNo);
    
    List<OrderView> list = null;
    list = orderViewService.getListByFilter(orderfilter);

    Map<String, Object> map = new HashMap<>();
    map.put("list", list);
    return map;
  }
  
//  @GetMapping("/ordersearch")
//  public Map<String, Object> searchList(@RequestParam(value="company") String[] company
//                         , @RequestParam(value="shippingway", defaultValue = "null") String[] shippingway
//                         , @RequestParam(value="unreleased", defaultValue = "null") String[] unreleased
//                         , @RequestParam(value="searchSelected", defaultValue = "null") String searchSelected
//                         , @RequestParam(value="searchContent", defaultValue = "null") String searchContent
//                         , @RequestParam(defaultValue = "1") int pageNo) {
//    
//    OrderFilter orderfilter = new OrderFilter();
//    orderfilter.setCompany(company);
//    orderfilter.setShippingway(shippingway);
//    orderfilter.setUnreleased(unreleased);
//    orderfilter.setSearchSelected(searchSelected);
//    orderfilter.setSearchContent(searchContent);
//    
//    int totalRows = orderViewService.getTotalNum();
//    Pager pager = new Pager(5, 5, totalRows, pageNo);
//    
//    List<OrderView> list = null;
//    list = orderViewService.getListByFilter(orderfilter);
//
//    Map<String, Object> map = new HashMap<>();
//    map.put("list", list);
//    return map;
//  }
}
