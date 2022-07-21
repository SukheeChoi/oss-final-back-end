package ows.edu.controller;

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

  @Autowired
  private OrderViewService orderViewService;
  
  //주문확인 현황
  @GetMapping("/status")
  public OrderStatus getStatus() {
    OrderStatus orderStatus = orderViewService.getStatus();
    
    Map<String, Object> map = new HashMap<>();
    map.put("status", orderStatus);
    return orderStatus;
  }
  
  //조건에 맞게 주문확인 리스트 보내주는 컨트롤러
  @GetMapping("/orderList")
  public Map<String, Object> getfilterList(OrderFilter orderFilter
                         , @RequestParam(defaultValue = "1") int pageNo
                         , @RequestParam(defaultValue = "5") int pageSize) {
    
    Map<String, Object> map = orderViewService.getListByFilter(orderFilter, pageNo, pageSize);
    

    return map;
  }
}
