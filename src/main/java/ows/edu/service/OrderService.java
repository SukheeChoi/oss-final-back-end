package ows.edu.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ows.edu.dto.Pager;

@Transactional
public interface OrderService {
	// '주문확인' 페이지의 페이지당 정보를 모두 리턴.
	public List<String> getOrderNoList(Pager pager);
	public int getTotalOrders();
}
