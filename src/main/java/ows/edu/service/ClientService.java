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
	
//	public List<Client> selectListAll(){
//		return clientDao.selectListAll();
//	};
	
//	List<Client> selectList(
//			String shippingCategory,
//			int status,
//			String unreleaseChk,
//			int orderNo,
//			String clientName
//		);
//	
//	List<Client> selectListByShippingCategory(
//			String shippingCategory,
//			String unreleaseChk,
//			int orderNo,
//			String clientName
//		);
	
	public List<Client> selectList(Map<String, Object> map){
		return clientDao.selectList(map);
	}
	
	public List<Client> selectListByShippingCategory(Map<String, Object> map){
		log.info("ClientService - map " + map.keySet());
		return clientDao.selectListByShippingCategory(map);
	}
	
	public List<Integer> statusCnt() {
		List<Integer> list = new ArrayList<>();
		for(int i=1; i<=5; i++) {
			list.add(clientDao.statusCnt(i));
		}
		return list;
	}
	
//	public List<Integer> statusCnt();
}