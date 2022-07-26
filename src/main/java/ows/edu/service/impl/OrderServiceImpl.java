package ows.edu.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;
import ows.edu.dao.ClientDao;
import ows.edu.dao.OrderDao;
import ows.edu.dao.ReleaseInspectionDao;
import ows.edu.dto.Client;
import ows.edu.dto.ReleaseInspection;
import ows.edu.service.OrderService;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

	@Resource
	OrderDao orderDao;
	@Resource
	ClientDao clientDao;
	@Resource
	ReleaseInspectionDao releaseInspectionDao;

	@Override
	public int getTotalOrders() {
		return orderDao.countProgressOrder();
	}

	// 현황(주문건)
	@Override
	public int count() {
		// TODO Auto-generated method stub
		return orderDao.countProgressOrder();
	}

	@Override
	public int pickingDoneCount() {
		// TODO Auto-generated method stub
		return orderDao.pickingDoneCount();
	}

	@Override
	public int pickDnCommonCount() {
		// TODO Auto-generated method stub
		return orderDao.pickDnCommonCount();
	}

	@Override
	public int pickDnEmergencyCount() {
		// TODO Auto-generated method stub
		return orderDao.pickDnEmergencyCount();
	}

	@Override
	public int rlsInspPackingCount() {
		// TODO Auto-generated method stub
		return orderDao.rlsInspPackingCount();
	}

	@Override
	public int rlsInspPackCommonCount() {
		// TODO Auto-generated method stub
		return orderDao.rlsInspPackCommonCount();
	}

	@Override
	public int rlsInspPackEmergencyCount() {
		// TODO Auto-generated method stub
		return orderDao.rlsInspPackEmergencyCount();
	}

	/**
	 * 패킹완료 최종 버튼을 눌렀을 시, 컨트롤러에 의해 호출된다. DAO를 호출하여 DB에 접근해 주문상태와 검수일시를 업데이트 한다. 업데이트
	 * 된 행의 수를 더하여 컨트롤러에게 전달한다.
	 * 
	 * @author 신현주
	 * @param orderNo 주문번호
	 * @return updateCount 업데이트 된 행수
	 * 
	 */
	@Transactional
	public int updateOrdSts(long orderNo) {
		int update1 = orderDao.updateOrdSts(orderNo);
		int update2 = releaseInspectionDao.updateReleaseInspectionDate(orderNo);
		return update1 + update2;
	}
}