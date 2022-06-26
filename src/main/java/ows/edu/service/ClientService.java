package ows.edu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ows.edu.dao.ClientDao;
import ows.edu.dto.Client;

@Service
public class ClientService {
	@Resource
	ClientDao clientDao;
	
//	public int count() {
//		return clientDao.count();
//	}
	
	public List<Client> selectList(){
		return clientDao.selectList();
	}
	
	public int statusCnt(int status) {
		return clientDao.statusCnt(status);
	}
}
