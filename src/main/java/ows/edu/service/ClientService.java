package ows.edu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import ows.edu.dao.ClientDao;
import ows.edu.dto.Client;

@Log4j2
@Service
public class ClientService {
	@Resource
	ClientDao clientDao;
	
	
	//주문 단계 중 한가지를 선택했을 때, 배송구분과 주문단계, 미출고로 필터링
	public List<Client> getList(Map<String, Object> map){
		return clientDao.getList(map);
	}
	
	//주문 단계를 전체 선택했을 때, 배송구분과 미출고로 필터링
	public List<Client> getListByShippingCategory(Map<String, Object> map){
		log.info("ClientService - map " + map.keySet());
		return clientDao.getListByShippingCategory(map);
	}
	
	public int unreleaseCnt() {
		return clientDao.unreleaseCnt();
	}
	
	//주문 단계 별(clientDao.statusCnt(i)) 건수 list에 삽입
	public List<Integer> getstatusCnt() {
		List<Integer> list = new ArrayList<>();
		for(int i=1; i<=5; i++) {
			list.add(clientDao.getstatusCnt(i));
		}
		return list;
	}
}