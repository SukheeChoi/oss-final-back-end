package ows.edu.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDao {
  public int count();

  // 미완료된 주문 건수 조회.
  public int countProgressOrder();
}