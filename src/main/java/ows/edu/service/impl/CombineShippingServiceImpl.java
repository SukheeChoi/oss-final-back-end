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
	public List<CombineShipping> getDeliveryList() {
		log.info("실행");
		List<CombineShipping> list = combineShippingDao.selectByDate();
		return list;
	}
	
}
