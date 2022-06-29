package ows.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ows.edu.dto.CombineShipping;
import ows.edu.dto.Vendor;

@Mapper
public interface CombineShippingDao {
	//수령 대상 업체정보(업체명, 업체코드) 조회. 날짜 필터링 포함.
	public List<Vendor> getVendorList(@Param("toDo") int toDo
									, @Param("startDate") String startDate
									, @Param("endDate") String endDate);

	// 담당자 필터링을 위한 조회.
//	public List<Employee> selectAssigneeListByDate(@Param("startDate") String startDate
	public List<String> selectAssigneeListByDate(@Param("toDo") int toDo
												, @Param("startDate") String startDate
												, @Param("endDate") String endDate);
	
	public List<String> selectReceiptOrderItemNoList(@Param("toDo") int toDo
													, @Param("employeeId") String employeeId
													, @Param("startDate") String startDate
													, @Param("endDate") String endDate);
	public CombineShipping selectAReceipt(String orderItemNo);
	
	public List<String> selectDeliveryOrderItemNoList(@Param("toDo") int toDo
													, @Param("employeeId") String employeeId
													, @Param("startDate") String startDate
													, @Param("endDate") String endDate);
	public CombineShipping selectADelivery(String orderItemNo);

	// 수령여부 update.
	public int updateAReceipt(CombineShipping combineShipping);
	
	// 전달여부 update.
	public int updateADelivery(int orderItemNo);


//	public List<CombineShipping> getReceiptListByDate(@Param("startDate") String startDate, @Param("endDate") String endDate);
}
