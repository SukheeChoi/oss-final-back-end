package ows.edu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import ows.edu.dao.CombineShippingDao;
import ows.edu.dao.OrderDao;
import ows.edu.dao.OrderItemDao;
import ows.edu.dto.CombineShipping;
import ows.edu.dto.CombineShippingPartner;
import ows.edu.dto.Employee;
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
	@Resource
	OrderDao orderDao;

	@Override
	public List<Vendor> getVendorList(int toDo, String[] dateList) {
		String startDate = null;
		String endDate = null;

		if (dateList.length == 2) {
			startDate = dateList[0];
			endDate = dateList[1];
		}
		List<Vendor> list = combineShippingDao.getVendorList(toDo, startDate, endDate);
		return list;
	}

	// 담당자 필터링을 위한 조회.
	@Override
	public List<Employee> getAssigneeList(int toDo, String[] dateList) {
		String startDate = null;
		String endDate = null;
		if (dateList.length == 2) {
			startDate = dateList[0];
			endDate = dateList[1];
		}
		List<Employee> list = combineShippingDao.selectAssigneeList(toDo, startDate, endDate);
		return list;
	}

	// '수령' 탭 선택된 경우.
	@Override
	public Map<String, Object> getReceiptList(int toDo, String vendorId, String[] dateList, int pageNo,
			int rowsPerPage) {
		log.info("실행");

		String startDate = null;
		String endDate = null;
		if (dateList.length == 2) {
			startDate = dateList[0];
			endDate = dateList[1];
		}
		// pagination을 위한 Pager객체 생성.
		int totalRows = combineShippingDao.selectCountAllReceipt(toDo, vendorId, startDate, endDate);
		Pager pager = new Pager(rowsPerPage, 10, totalRows, pageNo);

		List<String> orderItemNoList = combineShippingDao.selectReceiptList(toDo, vendorId, startDate, endDate, pager);

		Map<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("receiptList", orderItemNoList);

		return map;
	}

	// '전달' 탭 선택된 경우.
	@Override
	public Map<String, Object> getDeliveryList(int toDo, String employeeId, String[] dateList, int pageNo,
			int rowsPerPage) {
		String startDate = null;
		String endDate = null;
		if (dateList.length == 2) {
			startDate = dateList[0];
			endDate = dateList[1];
		}
		int totalRows = combineShippingDao.selectCountAllDelivery(toDo, employeeId, startDate, endDate);
		Pager pager = new Pager(rowsPerPage, 10, totalRows, pageNo);

		List<CombineShipping> orderItemNoList = new ArrayList<>();
		orderItemNoList = combineShippingDao.selectDeliveryList(toDo, employeeId, startDate, endDate, pager);

		Map<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("deliveryList", orderItemNoList);

		return map;
	}

	/**
	 * 수령여부 update.
	 * 
	 * @author 최숙희
	 */
	@Override
	public String updateReceipt(CombineShippingPartner[] combineShippingPartnerList) {
		String result = "fail";
		int totalAffectedRows = 0;
		for (CombineShippingPartner combineShippingPartner : combineShippingPartnerList) {
			int affectedRowNo = combineShippingDao.updateAReceipt(combineShippingPartner);
			if (affectedRowNo == 1) {
				totalAffectedRows++;
			}
			// 미출고 수량과 메모 업데이트.
			orderItemDao.update(combineShippingPartner);
//			orderItemDao.updateOiUnreleaseQuantity(combineShippingPartner);
		}
		if (totalAffectedRows == combineShippingPartnerList.length) {
			result = "success";

		}

		return result;
	}

	/**
	 * 전달여부 update. CS_DLV_QTY && CS_DLV_CHK && ((CS_RCV_URLS_QTY &&))
	 * CS_DLV_URLS_QTY 업데이트. TB_ORD_ITM OI_URLS_QTY 업데이트. TB_ORD ORD_STS 업데이트.(2->4)
	 * 
	 * @author 최숙희
	 */
	@Override
	public String updateDelivery(CombineShippingPartner[] combineShippingPartnerList) {
		log.info("실행");
		String result = "fail";
		int totalAffectedRows = 0;
		for (CombineShippingPartner combineShippingPartner : combineShippingPartnerList) {
			int affectedRowNo = combineShippingDao.updateADelivery(combineShippingPartner);
			if (affectedRowNo == 1) {
				totalAffectedRows++;
			}
			// 주문 상태값 변경.(2->4)
			int row = orderDao.updateOrderStatus(combineShippingPartner.getOrederNo(), 4);
			log.info("row : " + row);
		}
		//
		if (totalAffectedRows == combineShippingPartnerList.length) {
			result = "success";
		}
		return result;
	}

}
