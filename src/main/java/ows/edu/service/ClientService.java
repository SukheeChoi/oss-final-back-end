package ows.edu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ows.edu.dao.ClientDao;
import ows.edu.dto.Client;

@Service
public class ClientService {
	@Resource
	ClientDao clientDao;
	
//	public List<Client> selectListAll(){
//		return clientDao.selectListAll();
//	};
	
	public List<Client> selectList(Map<String, Object> map){
		return clientDao.selectList(map);
	}
	
	public List<Client> selectListByShippingCategory(Map<String, Object> map){
		return clientDao.selectListByShippingCategory(map);
	}
	
	public List<Integer> statusCnt() {
		List<Integer> list = new ArrayList<>();
		for(int i=1; i<=5; i++) {
			list.add(clientDao.statusCnt(i));
		}
		return list;
	}
}
