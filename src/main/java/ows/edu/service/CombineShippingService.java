package ows.edu.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import ows.edu.dto.CombineShippingPartner;
import ows.edu.dto.Vendor;

@Transactional
public interface CombineShippingService {
	//수령 대상 업체 조회.(날짜 필터링 동시에 수행.)
	public List<Vendor> getVendorList(int toDo, String[] dateList);
	//담당자 필터링을 위한 조회.
	public List<String> getAssigneeListByDate(int toDo, String[] dateList);
	
	// 담당사원 + 날짜정보 필터링 적용한 수령정보 조회.
	public Map<String, Object> getReceiptList(int toDo
												, String vendorName
												, String[] dateList
												, int pageNo);
	
	// 업체정보 + 날짜정보 필터링 적용한 전달정보 조회.
	public Map<String, Object> getDeliveryList(int toDo
											, String employeeName
											, String[] dateList
											, int pageNo
											);

	// 수령여부 update.
	public String updateReceipt(CombineShippingPartner[] combineShippingPartnerList);
//	public String updateReceipt(CombineShipping[] combineShippingList);

	// 전달여부 update.
	public String updateDelivery(int[] orderItemList);

	
}
