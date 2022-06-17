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
	
}
