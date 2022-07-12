package ows.edu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import ows.edu.dao.CombineShippingDao;
import ows.edu.dao.OrderItemDao;
import ows.edu.dto.CombineShipping;
import ows.edu.dto.Pager;
import ows.edu.dto.Vendor;
import ows.edu.service.CombineShippingService;

@Service
@Log4j2
public class CombineShippingServiceImpl implements CombineShippingService {
	
	@Resource
	CombineShippingDao combineShippingDao;
	@Resource
	OrderItemDao orderItemDao;
	
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
		List<String> list = combineShippingDao.selectAssigneeList(toDo, startDate, endDate);
//		List<Employee> list = combineShippingDao.selectAssigneeListByDate(startDate, endDate);
		log.info("service getAssigneeListByDate - list : " + list);
		return list;
	}
	
	// '수령' 탭 선택된 경우.
	@Override
	public Map<String, Object> getReceiptList(int toDo
									, String vendorName
									, String[] dateList
									, int pageNo
		) {
		log.info("실행");
		
		String startDate = null;
		String endDate = null;
		if(dateList.length == 2) {
			startDate = dateList[0];
			endDate = dateList[1];
		}
		// pagination을 위한 Pager객체 생성.
		int totalRows = combineShippingDao
				.selectCountAllReceipt(
					toDo, vendorName, startDate, endDate
				);
		log.info("## getReceiptList - totalRows" + totalRows);
		Pager pager = new Pager(20, 10, totalRows, pageNo);
		
		List<String> orderItemNoList = combineShippingDao
									.selectReceiptList(
											toDo
											, vendorName
											, startDate
											, endDate
											, pager
									);
		
		Map<String, Object> map = new HashMap<>();
//		if(orderItemNoList.isEmpty()) {
//			map.put("receiptList", null);
//		} else {
//			List<CombineShipping> receiptList = new ArrayList<>();
//			for(String orderItemNo : orderItemNoList) {
//				log.info("orderItemNo : " + orderItemNo);
//				receiptList.add(combineShippingDao.selectAReceipt(orderItemNo));
//			}
//			log.info("receiptList : " + receiptList);
//			map.put("receiptList", receiptList);
//		}
			map.put("pager", pager);
			map.put("receiptList", orderItemNoList);
		
		return map;
	}
	
	// '전달' 탭 선택된 경우.
	@Override
	public Map<String, Object> getDeliveryList(int toDo, String employeeName
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
								toDo, employeeName, startDate, endDate
							);
		Pager pager = new Pager(20, 10, totalRows, pageNo);
		
		List<CombineShipping> orderItemNoList = new ArrayList<>();
		orderItemNoList = combineShippingDao
				.selectDeliveryList(toDo, employeeName
				, startDate, endDate
				, pager);
		
		Map<String, Object> map = new HashMap<>();
//		if(orderItemNoList.isEmpty()) {
//			map.put("deliveryList", null);
//		} else {
//			List<CombineShipping> deliveryList = new ArrayList<>();
//			for(String orderItemNo : orderItemNoList) {
//				log.info("orderItemNo : " + orderItemNo);
//				deliveryList.add(combineShippingDao.selectADelivery(orderItemNo));
//				log.info("deliveryList : " + deliveryList);
//			}
//			map.put("deliveryList", deliveryList);
//		}
			map.put("pager", pager);
			map.put("deliveryList", orderItemNoList);
		
		return map;
	}

	// 수령여부 update: 수령 수량, 수령여부, 미출고 수량.
	// TB_CMB_SHP CS_RCV_QTY && CS_RCV_CHK && CS_RCV_URLS_QTY 업데이트.
	// TB_ORD_ITM OI_URLS_QTY 업데이트.
	@Override
	public String updateReceipt(CombineShipping[] combineShippingList) {
		String result = "fail";
		int totalAffectedRows = 0;
		for(CombineShipping combineShipping : combineShippingList) {
			int affectedRowNo = combineShippingDao.updateAReceipt(combineShipping);
			if(affectedRowNo == 1) {
				totalAffectedRows++;				
			}
			// 미출고 수량 업데이트.
			orderItemDao.updateOiUnreleaseQuantity(combineShipping);
		}
		if(totalAffectedRows == combineShippingList.length) {
			result = "success";
			
		}
		
		return result;
	}
	
	// 전달여부 update.
	// TB_CMB_SHP CS_DLV_QTY && CS_DLV_CHK && ((CS_RCV_URLS_QTY &&)) CS_DLV_URLS_QTY 업데이트.
	// TB_ORD_ITM OI_URLS_QTY 업데이트.
	// TB_ORD ORD_STS 업데이트.(2->4)
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

}
