package ows.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ows.edu.dto.CombineShipping;
import ows.edu.dto.CombineShippingPartner;
import ows.edu.dto.Employee;
import ows.edu.dto.Pager;
import ows.edu.dto.Vendor;

@Mapper
public interface CombineShippingDao {
	// 수령 대상 업체정보(업체명, 업체코드) 조회. 날짜 필터링 포함.
	public List<Vendor> getVendorList(@Param("toDo") int toDo, @Param("startDate") String startDate,
			@Param("endDate") String endDate);

	// 담당자 필터링을 위한 조회.
	public List<Employee> selectAssigneeList(@Param("toDo") int toDo, @Param("startDate") String startDate,
			@Param("endDate") String endDate);

	public int selectCountAllReceipt(@Param("toDo") int toDo, @Param("vendorId") String vendorId,
			@Param("startDate") String startDate, @Param("endDate") String endDate);

	// 수령 목록 조회.
	public List<String> selectReceiptList(@Param("toDo") int toDo, @Param("vendorId") String vendorId,
			@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("pager") Pager pager);

	public int selectCountAllDelivery(@Param("toDo") int toDo, @Param("employeeId") String employeeId,
			@Param("startDate") String startDate, @Param("endDate") String endDate);

	// 전달 목록 조회.
	public List<CombineShipping> selectDeliveryList(@Param("toDo") int toDo, @Param("employeeId") String employeeId,
			@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("pager") Pager pager);

	// 수령여부 update.
	public int updateAReceipt(CombineShippingPartner combineShippingPartner);

	// 전달여부 update.
	public int updateADelivery(CombineShippingPartner orderItemNo);

}
