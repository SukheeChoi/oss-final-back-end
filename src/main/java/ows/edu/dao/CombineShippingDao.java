package ows.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ows.edu.dto.CombineShipping;
import ows.edu.dto.Employee;

@Mapper
public interface CombineShippingDao {
	// 담당자 필터링을 위한 조회.
	public List<Employee> selectAssigneeListByDate();
	
	public List<String> selectDeliveryOrderItemNoList(String employeeId);
	public CombineShipping selectADelivery(String orderItemNo);
	
	public List<String> selectReceiptOrderItemNoList(String employeeId);
	public CombineShipping selectAReceipt(String orderItemNo);

	// 전달여부 update.
	public int updateADelivery(CombineShipping combineShipping);
}
