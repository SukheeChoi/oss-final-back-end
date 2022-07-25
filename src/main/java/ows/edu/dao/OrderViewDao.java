package ows.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ows.edu.dto.OrderFilter;
import ows.edu.dto.OrderView;
import ows.edu.dto.Pager;

@Mapper
public interface OrderViewDao {


  /**
   * 페이지에 맞는 주문 확인 반환
   * 
   * @author 이동현
   * @param orderfilter 회사/배송구분/미출고/검색조건/검색내용을 포함
   * @param pager 페이저 객체
   * @return 페이지에 해당하는 주문확인 목록 반환
   */
  public List<OrderView> selectByFilter(@Param("of") OrderFilter orderfilter, @Param("pager") Pager pager);
  
  /**
   * 전체 주문 개수 반환
   * 
   * @author 이동현
   * @param orderFilter 회사/배송구분/미출고/검색조건/검색내용을 포함
   * @return 전체 주문 개수 반환
   */
  public int count(@Param("of") OrderFilter orderFilter);
  
  /**
   * 전체 주문 현황 반환
   * 
   * @author 이동현
   * @return 전체 주문 현황 건 수 반환
   */
  public int countAll();
  
  /**
   * 오스템 직배송 현황 반환
   * 
   * @author 이동현
   * @return 오스템 직배송 현황 건 수 반환
   */
  public int countOsstem();
  
  /**
   * 합배송 현황 반환
   * 
   * @author 이동현
   * @return 합배송 현황 건 수 반환
   */
  public int countVendorPlus();
  
  /**
   * 협력사 직배송 현황 반환
   * 
   * @author 이동현
   * @return 협력사 직배송 현황 건 수 반환
   */
  public int countVendorDir();
  
  /**
   * 미출고 현황 반환
   * 
   * @author 이동현
   * @return 미출고 현황 건 수 반환
   */
  public int countunleased();
  
}
