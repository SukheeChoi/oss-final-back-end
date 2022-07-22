package ows.edu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ows.edu.dto.Client;

@Mapper
public interface ClientDao {
	//출고 리스트(긴급, 상태, 미출고로 필터링)
	public List<Client> getReleaseList(Map<String, Object> map);
	
	//미출고 리스트(긴급, 상태로 필터링)
	public List<Client> getUnreleaseList(Map<String, Object> map);
	
	//미출고 건수 조회
	public int unreleaseCnt();
	
	//주문 단계 별(clientDao.statusCnt(i)) 건수 list에 삽입
	public int getstatusCnt(int status);
}
