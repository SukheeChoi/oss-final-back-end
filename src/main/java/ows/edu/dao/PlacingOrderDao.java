package ows.edu.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ows.edu.dto.PlacingOrder;

@Mapper
public interface PlacingOrderDao {
  public PlacingOrder search();
  public List<PlacingOrder> searchAll();
  public List<PlacingOrder> searchByRecicveDate(String employeeId, Date receiveDate);
}
