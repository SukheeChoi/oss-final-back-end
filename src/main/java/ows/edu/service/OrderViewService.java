package ows.edu.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import ows.edu.dao.OrderViewDao;
import ows.edu.dto.OrderFilter;
import ows.edu.dto.OrderView;
import ows.edu.dto.Pager;

@Service
@Log4j2
public class OrderViewService {
  @Autowired
  private OrderViewDao orderViewDao;

  public List<OrderView> getList() {
    List<OrderView> list = new ArrayList<>();
    list.addAll(orderViewDao.select());
    log.info(list);
    return list;
  }
  
  public List<OrderView> getList(Pager pager) {
    List<OrderView> list = new ArrayList<>();
    list.addAll(orderViewDao.selectByPage(pager));
    log.info(list);
    return list;
  }
  
  public List<OrderView> getListByFilter(OrderFilter orderfilter) {
    List<OrderView> list = new ArrayList<>();
    //오스템 제품
    if(Arrays.toString(orderfilter.getCompany()).contains("osstemItem")) {
      orderfilter.setItemOSS(1);
    }
    //오스템 상품
    if(Arrays.toString(orderfilter.getCompany()).contains("osstemProduct")) {
      orderfilter.setItemOSSPRO(1);
    }
    //협력사 직배송
    if(Arrays.toString(orderfilter.getCompany()).contains("vendorproductDir")) {
      orderfilter.setItemVND(1);
    }
    //협력사 합배송
    if(Arrays.toString(orderfilter.getCompany()).contains("vendorproductPlus")) {
      orderfilter.setItemVNDPLUS(1);
    }
    
    //긴급 일반 조건 검색
    if(orderfilter.getShippingway().length != 2) {
      if(Arrays.toString(orderfilter.getShippingway()).contains("emergency")) {
        orderfilter.setOrderCatagory(1);
      } 
      if(Arrays.toString(orderfilter.getShippingway()).contains("normal")) {
        orderfilter.setOrderCatagory(2);
      }
    }
    log.info(orderfilter.getShippingway().length);
    
    //출고 미출고 조건 검색
    if(orderfilter.getUnreleased().length != 2) {
      if(Arrays.toString(orderfilter.getUnreleased()).contains("released")) {
        orderfilter.setPickingdirectionUnreleased(1);
      } 
      if(Arrays.toString(orderfilter.getUnreleased()).contains("unreleased")) {
        orderfilter.setPickingdirectionUnreleased(2);
      }
    }
    
    log.info(orderfilter);
    
    list.addAll(orderViewDao.selectByFilter(orderfilter));
    return list;
  }
  
  public int getTotalNum() {
    return orderViewDao.count();
  }
}
