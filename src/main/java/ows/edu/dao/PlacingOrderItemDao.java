package ows.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ows.edu.dto.PlacingOrderItem;

@Mapper
public interface PlacingOrderItemDao {
  
  public PlacingOrderItem search();
  public List<PlacingOrderItem> searchAll();
}
