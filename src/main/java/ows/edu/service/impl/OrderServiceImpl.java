package ows.edu.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import ows.edu.dao.OrderDao;
import ows.edu.service.OrderService;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
	
	@Resource
	OrderDao orderDao;
	
//	@Override
//	public List<String> getOrderNoList(Pager pager) {
//	List<String> list = new ArrayList<>();
//	orderDao.selectByPage(pager);
//	
//		return list;
//	}

	@Override
	public int getTotalOrders() {
		return orderDao.count();
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return orderDao.count();
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
	
}
