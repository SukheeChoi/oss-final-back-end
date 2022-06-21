package ows.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ows.edu.dto.OrderView;

@Mapper
public interface OrderViewDao {
  public List<OrderView> search();
}
