package ows.edu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import ows.edu.dao.CombineShippingDao;
import ows.edu.dto.CombineShipping;
import ows.edu.service.CombineShippingService;

@Service
@Log4j2
public class CombineShippingServiceImpl implements CombineShippingService {
	
	@Resource
	CombineShippingDao combineShippingDao;
	
	@Override
	public List<String> getDeliveryOrderItemNoList(String employeeId) {
		log.info("실행");
		List<String> list = combineShippingDao.selectDeliveryOrderItemNoList(employeeId);
		return list;
	}
	
	@Override
	public CombineShipping getADelivery(String orderItemNo) {
		log.info("실행");
		CombineShipping combineShipping = combineShippingDao.selectADelivery(orderItemNo);
		return combineShipping;
	}
	
}
