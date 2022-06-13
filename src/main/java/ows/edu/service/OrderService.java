package ows.edu.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface OrderService {
	public List<String> getOrderNoList(int pageNo);
}
