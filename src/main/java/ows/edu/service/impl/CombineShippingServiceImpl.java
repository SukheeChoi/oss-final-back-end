package ows.edu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import ows.edu.dao.CombineShippingDao;
import ows.edu.dto.CombineShipping;
import ows.edu.dto.Pager;
import ows.edu.dto.Vendor;
import ows.edu.service.CombineShippingService;

@Service
@Log4j2
public class CombineShippingServiceImpl implements CombineShippingService {
	
	@Resource
	CombineShippingDao combineShippingDao;
	
	@Override
	public List<Vendor> getVendorList(int toDo, String[] dateList) {
		log.info("실행");
		String startDate = null;
		String endDate = null;
		if(dateList.length == 2) {
			startDate = dateList[0];
			endDate = dateList[1];
		}
		List<Vendor> list = combineShippingDao.getVendorList(toDo, startDate, endDate);
		return list;
	}
	
	// 담당자 필터링을 위한 조회.
	@Override
	public List<String> getAssigneeListByDate(int toDo, String[] dateList) {
//		public List<Employee> getAssigneeListByDate(String[] dateList) {
		log.info("실행");
		String startDate = null;
		String endDate = null;
		log.info("dateList.length" + dateList.length);
		if(dateList.length == 2) {
			startDate = dateList[0];
			endDate = dateList[1];
		}
		List<String> list = combineShippingDao.selectAssigneeListByDate(toDo, startDate, endDate);
//		List<Employee> list = combineShippingDao.selectAssigneeListByDate(startDate, endDate);
		log.info("service getAssigneeListByDate - list : " + list);
		return list;
	}
	
	// '수령' 탭 선택된 경우.
	@Override
	public List<String> getReceiptOrderItemNoList(int toDo, String employeeId, String[] dateList) {
		log.info("실행");
		String startDate = null;
		String endDate = null;
		if(dateList.length == 2) {
			startDate = dateList[0];
			endDate = dateList[1];
		}
		List<String> list = combineShippingDao.selectReceiptOrderItemNoList(toDo, employeeId, startDate, endDate);
		
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
	public Map<String, Object> getDeliveryList(int toDo, String employeeId
										, String[] dateList, int pageNo) {
		log.info("실행");
		String startDate = null;
		String endDate = null;
		if(dateList.length == 2) {
			startDate = dateList[0];
			endDate = dateList[1];
		}
		// pagination을 위한 Pager객체 생성.
		int totalRows = combineShippingDao
							.selectCountAllDelivery(
								toDo, employeeId, startDate, endDate
							);
		Pager pager = new Pager(20, 10, totalRows, pageNo);
		
		// 필요한 OI_NO 조회해서 List로 받아옴.
		//날짜 필터링이 선택되지 않은 경우이므로, 당일의 정보만을 조회.
		List<String> orderItemNoList = new ArrayList<>();
		orderItemNoList = combineShippingDao
				.selectDeliveryOrderItemNoList(toDo, employeeId
				, startDate, endDate
				, pager);
		
		Map<String, Object> map = new HashMap<>();
		if(orderItemNoList.isEmpty()) {
			map.put("deliveryList", null);
		} else {
			List<CombineShipping> deliveryList = new ArrayList<>();
			for(String orderItemNo : orderItemNoList) {
				log.info("orderItemNo : " + orderItemNo);
				deliveryList.add(combineShippingDao.selectADelivery(orderItemNo));
				log.info("deliveryList : " + deliveryList);
			}
			map.put("deliveryList", deliveryList);
		}
		
		
		return map;
	}
	
//	@Override
//	public CombineShipping getADelivery(String orderItemNo) {
//		log.info("실행");
//		CombineShipping combineShipping = combineShippingDao.selectADelivery(orderItemNo);
//		return combineShipping;
//	}

	// 수령여부 update: 미출고, 수령여부 컬럼.
	@Override
	public String updateReceipt(CombineShipping[] combineShippingList) {
		String result = "fail";
		int totalAffectedRows = 0;
		for(CombineShipping combineShipping : combineShippingList) {
			int affectedRowNo = combineShippingDao.updateAReceipt(combineShipping);
			if(affectedRowNo == 1) {
				totalAffectedRows++;				
			}
		}
		if(totalAffectedRows == combineShippingList.length) {
			result = "success";
		}
		
		return result;
	}
	
	// 전달여부 update.
	@Override
	public String updateDelivery(int[] orderItemNoList) {
		log.info("실행");
		String result = "fail";
		int totalAffectedRows = 0;
		for(int orderItemNo : orderItemNoList) {
			int affectedRowNo = combineShippingDao.updateADelivery(orderItemNo);
			if(affectedRowNo == 1) {
				totalAffectedRows++;				
			}
		}
		if(totalAffectedRows == orderItemNoList.length) {
			result = "success";
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
