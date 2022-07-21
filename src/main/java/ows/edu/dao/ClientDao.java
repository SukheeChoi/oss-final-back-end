package ows.edu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ows.edu.dto.Client;
import ows.edu.dto.Pager;

@Mapper
public interface ClientDao {
	//주문 단계 중 한가지를 선택했을 때, 배송구분과 주문단계, 미출고로 필터링
	public List<Client> getList(Map<String, Object> map);
	
	//주문 단계를 전체 선택했을 때, 배송구분과 미출고로 필터링
	public List<Client> getListByShippingCategory(Map<String, Object> map);
//	public List<HashMap<String, String>> getListByShippingCategory(
//			String[] shippingCategory,
//			boolean unrelease,
//			Long orderNo,
//			String clientName,
//			Pager pager,
//			int pageSize
//			);

	public int getPageRows(
		String[] shippingCategory,
		int status,
		boolean unrelease,
		Long orderNo,
		String clientName,
		int pageNo
		);
	
	//미출고 건수 조회
	public int unreleaseCnt();
	
	//주문 단계 별(clientDao.statusCnt(i)) 건수 list에 삽입
	public int getstatusCnt(int status);
}
