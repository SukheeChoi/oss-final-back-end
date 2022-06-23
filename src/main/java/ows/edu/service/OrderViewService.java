package ows.edu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import ows.edu.dao.OrderViewDao;
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
  
  public int getTotalNum() {
    return orderViewDao.count();
  }
}
