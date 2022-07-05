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

  @Autowired
  private OrderViewService orderViewService;
  
  //주문확인 현황
  @GetMapping("/orderStatus")
  public OrderStatus getStatus() {
    log.info("실행");
    
    OrderStatus orderStatus = orderViewService.getStatus();
    
    Map<String, Object> map = new HashMap<>();
    map.put("list", orderStatus);
    return orderStatus;
  }
  
  //조건에 맞게 주문확인 리스트 보내주는 컨트롤러
  @GetMapping("/orderfilter")
  public Map<String, Object> getfilterList(@RequestParam(value="company", defaultValue = "null") String[] company
                         , @RequestParam(value="shippingway", defaultValue = "null") String[] shippingway
                         , @RequestParam(value="unreleased", defaultValue = "null") String[] unreleased
                         , @RequestParam(value="searchSelected", defaultValue = "null") String searchSelected
                         , @RequestParam(value="searchContent", defaultValue = "null") String searchContent
                         , @RequestParam(defaultValue = "1") int pageNo
                         , @RequestParam(defaultValue = "5") int pageSize) {
    
    OrderFilter orderfilter = new OrderFilter();
    orderfilter.setCompany(company);
    orderfilter.setShippingway(shippingway);
    orderfilter.setUnreleased(unreleased);
    orderfilter.setSearchSelected(searchSelected);
    orderfilter.setSearchContent(searchContent);
    
    log.info(orderfilter);
    int totalRows = orderViewService.getTotalNum(orderfilter);
    Pager pager = new Pager(pageSize, 5, totalRows, pageNo);
    log.info(pager);
    
    List<OrderView> list = null;
    list = orderViewService.getListByFilter(orderfilter, pager);
    Map<String, Object> map = new HashMap<>();
    map.put("data", list);
    map.put("totalCount", totalRows);
    log.info("map : " + map);
    return map;
  }
  
}
