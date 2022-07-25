package ows.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ows.edu.dto.ClientDetail;
import ows.edu.dto.ClientOrder;
import ows.edu.dto.ClientOrderDetail;
import ows.edu.dto.SelectedOrder;

@Mapper
public interface ClientModalDao {

  /**
   * 거래처 정보 반환
   * 
   * @author 이동현
   * @param clientNo 고객번호
   * @return 고객번호에 해당하는 거래처 정보를 반환함
   */
  public ClientDetail searchClientDetail(int clientNo);
  
  /**
   * 진행 주문 정보 반환
   * 
   * @author 이동현
   * @param orderNo 주문번호
   * @return 주문번호에 해당하는 진행 주문 정보 목록 반환
   */
  public List<SelectedOrder> searchSelectedOrder(String orderNo);
  
  /**
   * 전체 주문 이력 반환
   * 
   * @author 이동현
   * @param clientNo 고객번호
   * @param orderNo 주문번호
   * @return 고객번호에 해당하는 모든 주문 이력 목록 반환(선택한 주문 제외)
   */
  public List<ClientOrder> searchAllClientOrder(int clientNo, String orderNo);
  
  /**
   * 주문 이력 상세 정보 반환
   * 
   * @author 이동현
   * @param orderNo 주문번호
   * @return 주문번호에 해당하는 주문 이력 상세 정보 목록 반환
   */
  public List<ClientOrderDetail> searchAllClientOrderDetail(String orderNo);
}
