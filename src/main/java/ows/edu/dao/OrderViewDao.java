package ows.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ows.edu.dto.OrderFilter;
import ows.edu.dto.OrderView;
import ows.edu.dto.Pager;

@Mapper
public interface OrderViewDao {

  //전체 주문 확인 리스트 가져오기
  public List<OrderView> selectByFilter(@Param("of") OrderFilter orderfilter, @Param("pager") Pager pager);
  //전체 주문 확인 리스트 가져오기
  public int count(@Param("of") OrderFilter orderFilter);
  
  public int countAll();
  public int countOsstem();
  public int countVendorPlus();
  public int countVendorDir();
  public int countunleased();
  
}
