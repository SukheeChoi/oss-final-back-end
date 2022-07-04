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
	public List<Client> selectList(Map<String, Object> map){
		return clientDao.selectList(map);
	}
	
	//주문 단계를 전체 선택했을 때, 배송구분과 미출고로 필터링
	public List<Client> selectListByShippingCategory(Map<String, Object> map){
		log.info("ClientService - map " + map.keySet());
		return clientDao.selectListByShippingCategory(map);
	}
	
	//주문 단계 별(clientDao.statusCnt(i)) 건수 list에 삽입
	public List<Integer> statusCnt() {
		List<Integer> list = new ArrayList<>();
		for(int i=1; i<=5; i++) {
			list.add(clientDao.statusCnt(i));
		}
		return list;
	}
}