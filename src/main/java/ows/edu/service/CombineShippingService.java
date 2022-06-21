package ows.edu.service;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ows.edu.dto.CombineShipping;
import ows.edu.dto.Employee;

@Transactional
public interface CombineShippingService {
	//담당자 필터링을 위한 조회.
	public List<Employee> getAssigneeListByDate();
	
	public List<String> getDeliveryOrderItemNoList(String employeeId);
	public CombineShipping getADelivery(String orderItemNo);
	
	public List<String> getReceiptOrderItemNoList(String employeeId);
	public CombineShipping getAReceipt(String orderItemNo);

	// 전달여부 update.
	public String updateDelivery(CombineShipping[] itemOrderNOList);
	// 선택된 기간 동안의 수령목록 조회.
	public List<CombineShipping> getReceiptListByDate(String[] dateList);
}
