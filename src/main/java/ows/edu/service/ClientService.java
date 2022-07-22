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
	
	
	//출고 리스트(긴급, 상태, 미출고로 필터링)
	public List<Client> getReleaseList(Map<String, Object> map){
		return clientDao.getReleaseList(map);
	}
	
	//미출고 리스트(긴급, 상태로 필터링)
	public List<Client> getUnreleaseList(Map<String, Object> map){
		return clientDao.getUnreleaseList(map);
	}

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