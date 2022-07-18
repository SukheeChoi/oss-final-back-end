package ows.edu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import ows.edu.dao.ClientDao;
import ows.edu.dto.Client;
import ows.edu.dto.Pager;

@Log4j2
@Service
public class ClientService {
	@Resource
	ClientDao clientDao;
	
	
	//주문 단계 중 한가지를 선택했을 때, 배송구분과 주문단계, 미출고로 필터링
	public List<Client> getList(Map<String, Object> map){
		return clientDao.getList(map);
	}
//	public List<HashMap<String, String>> getList(
//			String[] shippingCategory,
//			int status,
//			boolean unrelease,
//			Long orderNo,
//			String clientName,
//			Pager pager,
//			int pageSize
//			) {
//		return clientDao.getList(shippingCategory, status, unrelease, orderNo, clientName, pager, pageSize);
//	};
	
	//주문 단계를 전체 선택했을 때, 배송구분과 미출고로 필터링
	public List<Client> getListByShippingCategory(Map<String, Object> map){
		log.info("ClientService - map " + map.keySet());
		return clientDao.getListByShippingCategory(map);
	}
//	public List<HashMap<String, String>> getListByShippingCategory(
//			String[] shippingCategory,
//			boolean unrelease,
//			Long orderNo,
//			String clientName,
//			Pager pager,
//			int pageSize
//			) {
//		return clientDao.getListByShippingCategory(shippingCategory, unrelease, orderNo, clientName, pager, pageSize);
//	};

	//미출고 건수 조회
	public int unreleaseCnt() {
		return clientDao.unreleaseCnt();
	}
	
	//주문 단계 별(clientDao.statusCnt(i)) 건수 list에 삽입
	public List<Integer> getstatusCnt() {
		List<Integer> list = new ArrayList<>();
		for(int i=0; i<=6; i++) {
			list.add(clientDao.getstatusCnt(i));
		}
		return list;
	}
	
}