package ows.edu.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ows.edu.dto.CombineShipping;
import ows.edu.dto.Employee;
import ows.edu.dto.Vendor;

@Mapper
public interface CombineShippingDao {
	//수령 대상 업체정보(업체명, 업체코드) 조회. 날짜 필터링 포함.
	public List<Vendor> getVendorList(@Param("strNowDateList") String[] strNowDateList);

	// 담당자 필터링을 위한 조회.
	public List<Employee> selectAssigneeListByDate();
	
	public List<String> selectDeliveryOrderItemNoList(String employeeId);
	public CombineShipping selectADelivery(String orderItemNo);
	
	public List<String> selectReceiptOrderItemNoList(String employeeId);
	public CombineShipping selectAReceipt(String orderItemNo);

	// 전달여부 update.
	public int updateADelivery(CombineShipping combineShipping);

	public List<CombineShipping> getReceiptListByDate(@Param("startDate") String startDate, @Param("endDate") String endDate);
}
