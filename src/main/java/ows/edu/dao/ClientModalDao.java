package ows.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ows.edu.dto.ClientDetail;
import ows.edu.dto.ClientOrder;
import ows.edu.dto.ClientOrderDetail;
import ows.edu.dto.SelectedOrder;

@Mapper
public interface ClientModalDao {

  //거래처 정보
  public ClientDetail searchClientDetail(int clientNo);
  
  //진행 주문 정보
  public List<SelectedOrder> searchSelectedOrder(String orderNo);
  
  //과거 주문 이력
  public List<ClientOrder> searchAllClientOrder(int clientNo, String orderNo);
  
  //상세내역
  public List<ClientOrderDetail> searchAllClientOrderDetail(String orderNo);
}
