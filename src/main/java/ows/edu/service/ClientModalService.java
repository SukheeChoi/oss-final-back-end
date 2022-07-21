package ows.edu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import ows.edu.dao.ClientModalDao;
import ows.edu.dto.ClientDetail;
import ows.edu.dto.ClientOrder;
import ows.edu.dto.ClientOrderDetail;
import ows.edu.dto.SelectedOrder;

@Log4j2
@Service
public class ClientModalService {

  @Autowired
  private ClientModalDao clientModalDao;
  
  //모달 정보
  public Map<String, Object> getModal(int clientNo, String orderNo) {
    Map<String, Object> map = new HashMap<>();
    
    //거래처 정보
    ClientDetail clientDetail = clientModalDao.searchClientDetail(clientNo);

    //클릭한 주문 정보
    List<SelectedOrder> selectedOrder = clientModalDao.searchSelectedOrder(orderNo);
    
    //전체 주문 이력(클릭한 주문 제외)
    List<ClientOrder> clientOrder = clientModalDao.searchAllClientOrder(clientNo, orderNo);
    
    map.put("clientDetail", clientDetail);
    map.put("selectedOrder", selectedOrder);
    map.put("clientOrder", clientOrder);
    return map;
  }
    
  //상세내역
  public List<ClientOrderDetail> getClientOrderDetailByOrderNo(String orderNo) {
    return clientModalDao.searchAllClientOrderDetail(orderNo);
  };
}
