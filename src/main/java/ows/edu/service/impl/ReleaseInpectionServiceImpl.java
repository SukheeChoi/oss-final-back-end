package ows.edu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import ows.edu.dao.OrderDao;
import ows.edu.dao.PackingDao;
import ows.edu.dao.PickingDirectionDao;
import ows.edu.dao.ReleaseInspectionDao;
import ows.edu.dto.ReleaseInspection;
import ows.edu.service.ReleaseInspectionService;

@Service
@Log4j2
public class ReleaseInpectionServiceImpl implements ReleaseInspectionService {
	
	@Resource
	ReleaseInspectionDao releaseInspectionDao;
	@Resource
	OrderDao orderDao;
	@Resource
	PickingDirectionDao pickingDirectionDao;
	@Resource
	PackingDao packingDao;
	
	@Override
	public Map<String, Object> getSummary() {
		// 전반적으로 TB_ORD의 STS값을 활용하는 방식으로 변경할 것.
		// (현재는 데이터가 정확하지 않은 상태라 JOIN을 이용.)
		
		Map<String, Object> map = new HashMap<>();
		// 주문건수 조회.
		int progressOrderNo  = orderDao.countProgressOrder();
		map.put("progressOrderNo", progressOrderNo);
		// 피킹지시 건수 조회.(ORD_PRC_ORD IS NULL && )
		int pickingDirectionNo = pickingDirectionDao.countPickingDirection();
		map.put("pickingDirectionNo", pickingDirectionNo);
		// 출고검수 + 패킹 건수 조회.(출고검수가 완료되어야 패킹을 진행하기 때문에 출고검수 건수를 사용.)
		int releaseInspectionNo = releaseInspectionDao.countReleaseInspection();
//		int packingNo = packingDao.countPackingNo();
		map.put("releaseInspectionNo", releaseInspectionNo);
		// 미출고 건수 조회.(
		// 	이전 단계의 미출고가 0건이 되어야 다음 단계로 진행할 수 있기 때문에
		// 	현재 처리단계를 확인하지 않고, 출고검수 미출고와 패킹의 미출고 건수를 합함.
		//	null 주의. 
		// )
		int releaseInspectionUnreleased = releaseInspectionDao.sumUnreleased();
		int packingUnreleased = packingDao.sumUnreleased();
		int unreleasedNo = releaseInspectionUnreleased + packingUnreleased;
		map.put("unreleasedNo", unreleasedNo);
		
		// 긴급 건수 조회.
		int expressShippingNo = releaseInspectionDao.countExpressShipping();
		map.put("expressShippingNo", expressShippingNo);
		// 일반 건수 조회.
		int normalShippingNo = releaseInspectionDao.countNormalShipping();
		map.put("normalShippingNo", normalShippingNo);
		
		
		return map;
	}

	@Override
	public List<ReleaseInspection> getAfterPickingList(
		String shippingCategory
		, String shippingWay
		, String released
		, String assignee
		, int orderNo
		, String clientName
		, String shippingDestination
		, String vendorName
	) {
		String strOrderNo = Integer.toString(orderNo);
//		log.info("strOrderNo : " + strOrderNo);
		
//		log.info("assignee : " + assignee);
//		log.info("clientName : " + clientName);
//		log.info("shippingDestination : " + shippingDestination);
		List<ReleaseInspection> list = releaseInspectionDao
			.selectAfterPickingList(
				shippingCategory
				, shippingWay
				, released
				, assignee
//				, orderNo
				
				, strOrderNo
				
				, clientName
				, shippingDestination
				, vendorName
			);
		return list;
	}

	
}
