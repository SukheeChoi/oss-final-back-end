package ows.edu.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import ows.edu.dao.CombineShippingDao;
import ows.edu.dto.CombineShipping;
import ows.edu.dto.Employee;
import ows.edu.dto.Vendor;
import ows.edu.service.CombineShippingService;

@Service
@Log4j2
public class CombineShippingServiceImpl implements CombineShippingService {
	
	@Resource
	CombineShippingDao combineShippingDao;
	
	@Override
	public List<Vendor> getVendorList(String[] strNowDateList) {
		List<Vendor> list = combineShippingDao.getVendorList(strNowDateList);
		return list;
	}
	
	// 담당자 필터링을 위한 조회.
	@Override
	public List<Employee> getAssigneeListByDate() {
		log.info("실행");
		List<Employee> list = combineShippingDao.selectAssigneeListByDate();
		return list;
	}
	
	// '수령' 탭 선택된 경우.
	@Override
	public List<String> getReceiptOrderItemNoList(String employeeId, String[] dateList) {
		log.info("실행");
		List<String> list = combineShippingDao.selectReceiptOrderItemNoList(employeeId, dateList);
		return list;
	}
	
	@Override
	public CombineShipping getAReceipt(String orderItemNo) {
		log.info("실행");
		CombineShipping combineShipping = combineShippingDao.selectAReceipt(orderItemNo);
		return combineShipping;
	}
	// '전달' 탭 선택된 경우.
	@Override
	public List<String> getDeliveryOrderItemNoList(String employeeId, String[] dateList) {
		log.info("실행");
		List<String> list = combineShippingDao.selectDeliveryOrderItemNoList(employeeId, dateList);
		return list;
	}
	
	@Override
	public CombineShipping getADelivery(String orderItemNo) {
		log.info("실행");
		CombineShipping combineShipping = combineShippingDao.selectADelivery(orderItemNo);
		return combineShipping;
	}

	// 전달여부 update.
	@Override
	public String updateDelivery(CombineShipping[] combineShippingList) {
		log.info("실행");
		String result = null;
		int totalAffectedRows = 0;
		for(CombineShipping combineShipping : combineShippingList) {
			int affectedRowNo = combineShippingDao.updateADelivery(combineShipping);
			if(affectedRowNo == 1) {
				totalAffectedRows++;				
			}
		}
		if(totalAffectedRows == combineShippingList.length) {
			result = "success";
		} else {
			result = "fail";
		}
		return result;
	}

//	@Override
//	public List<CombineShipping> getReceiptListByDate(String[] dateList) {
//		String startDate = dateList[0];
//		String endDate = dateList[1];
//		List<CombineShipping> list = combineShippingDao.getReceiptListByDate(startDate, endDate);
//		return list;
//	}

}