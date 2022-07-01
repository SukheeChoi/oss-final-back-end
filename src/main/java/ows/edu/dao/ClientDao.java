package ows.edu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ows.edu.dto.Client;

@Mapper
public interface ClientDao {
//	public List<Client> selectListAll();
	
//	public List<Client> selectList(
//			String shippingCategory,
//			int status,
//			String unreleaseChk,
//			int orderNo,
//			String clientName
//		);
//	
//	public List<Client> selectListByShippingCategory(
//			String shippingCategory,
//			String unreleaseChk,
//			int orderNo,
//			String clientName
//		);
	
	public List<Client> selectList(Map<String, Object> map);
	
	public List<Client> selectListByShippingCategory(Map<String, Object> map);
	
	public int statusCnt(int status);
}
