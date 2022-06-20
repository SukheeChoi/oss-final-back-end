package ows.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ows.edu.dto.CombineShipping;

@Mapper
public interface CombineShippingDao {
	public List<String> selectDeliveryOrderItemNoList(String employeeId);
	public CombineShipping selectADelivery(String orderItemNo);
	
	public List<String> selectReceiptOrderItemNoList(String employeeId);
}
