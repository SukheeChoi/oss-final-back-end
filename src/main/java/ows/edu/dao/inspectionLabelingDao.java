package ows.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ows.edu.dto.InspectionLabeling;

@Mapper
public interface inspectionLabelingDao {
  public List<InspectionLabeling> search(List<String> placeOrderItemNo);
}
