package ows.edu.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ows.edu.dto.CombineShipping;
import ows.edu.dto.Employee;
import ows.edu.dto.Vendor;

@Transactional
public interface CombineShippingService {
	//수령 대상 업체 조회.(날짜 필터링 동시에 수행.)
	public List<Vendor> getVendorList(String[] strNowDateList);
	//담당자 필터링을 위한 조회.
	public List<Employee> getAssigneeListByDate();
	
	// 담당사원 + 날짜정보 필터링 적용한 수령정보 조회.
	public List<String> getReceiptOrderItemNoList(String employeeId, String[] dateList);
	public CombineShipping getAReceipt(String orderItemNo);
	
	// 업체정보 + 날짜정보 필터링 적용한 전달정보 조회.
	public List<String> getDeliveryOrderItemNoList(String employeeId, String[] dateList);
	public CombineShipping getADelivery(String orderItemNo);

	// 전달여부 update.
	public String updateDelivery(CombineShipping[] itemOrderNOList);
	// 선택된 기간 동안의 수령목록 조회.
//	public List<CombineShipping> getReceiptListByDate(String[] dateList);

}