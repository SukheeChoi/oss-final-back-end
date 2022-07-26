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
	
	/**
	 * 주문 리스트(긴급, 상태, 미출고, 주문번호, 거래처명으로 필터링)
	 * 
	 * @author 김예원
	 * @param Map<String, Object> 필터링 객체를 담은 map
	 * @return List<Client> 필터링한 데이터
	 */
	public List<Client> getFilteredList(Map<String, Object> map){
		return clientDao.getFilteredList(map);
	}

	/**
	 * 미출고 건수 조회
	 * 
	 * @author 김예원
	 * @return unreleaseCnt 미출고 건수
	 */
	public int unreleaseCnt() {
		return clientDao.unreleaseCnt();
	}
	
	/**
	 * 주문 단계 별(clientDao.statusCnt(i)) 건수 list에 삽입
	 * 
	 * @author 김예원
	 * @return List<Integer> 주문 단계 별 건수 
	 */
	public List<Integer> getstatusCnt() {
		List<Integer> list = new ArrayList<>();
		for(int i=1; i<=6; i++) {
			list.add(clientDao.getstatusCnt(i));
		}
		return list;
	}
}