package ows.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ows.edu.dto.ClientDetail;
import ows.edu.dto.PastOrder;
import ows.edu.dto.PastOrderDetail;
import ows.edu.dto.RecentOrder;

@Mapper
public interface ClientModalDao {

  //거래처 정보
  public ClientDetail searchClientDetail(int clientNo);
  
  //진행 주문 정보
  public RecentOrder searchRecentOrder(String orderNo);
  
  //과거 주문 이력
  public List<PastOrder> searchAllPastOrder(int clientNo);
  
  //상세내역
  public List<PastOrderDetail> searchAllPasOrderDetail(String orderNo);
}
