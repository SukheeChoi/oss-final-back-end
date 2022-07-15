package ows.edu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import ows.edu.dao.ClientModalDao;
import ows.edu.dto.ClientDetail;
import ows.edu.dto.PastOrder;
import ows.edu.dto.PastOrderDetail;
import ows.edu.dto.RecentOrder;

@Log4j2
@Service
public class ClientModalService {

  @Autowired
  private ClientModalDao clientModalDao;
  
  //거래처 정보
  public ClientDetail getClientDetailByClientNo(int clientNo) {
    return clientModalDao.searchClientDetail(clientNo);
  };
  
  //진행 주문 정보
  public List<RecentOrder> getRecentOrderByOrderNo(String orderNo) {
    return clientModalDao.searchRecentOrder(orderNo);
  };
  
  //과거 주문 이력
  public List<PastOrder> getPastOrderListByClientNo(int clientNo, String orderNo) {
    return clientModalDao.searchAllPastOrder(clientNo, orderNo);
  };
  
  //상세내역
  public List<PastOrderDetail> getPastOrderDetailByOrderNo(String orderNo) {
    return clientModalDao.searchAllPasOrderDetail(orderNo);
  };
}
