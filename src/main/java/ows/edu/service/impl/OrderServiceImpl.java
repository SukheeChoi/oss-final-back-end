package ows.edu.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import ows.edu.service.OrderService;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
	
	@Resource
	
	@Override
	public List<String> getOrderNoList(int pageNo) {
	List<String> list = new ArrayList<>();
	
	
	return list;
	}
	
}
