package ows.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ows.edu.dto.OrderView;
import ows.edu.dto.Pager;

@Mapper
public interface OrderViewDao {
  public List<OrderView> select(); 
  public List<OrderView> selectByPage(Pager pager);
  public int count();
}
