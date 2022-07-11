package ows.edu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ows.edu.dto.Client;

@Mapper
public interface ClientDao {
	public List<Client> getList(Map<String, Object> map);
	
	public List<Client> getListByShippingCategory(Map<String, Object> map);
	
	public int unreleaseCnt();
	
	public int getstatusCnt(int status);
}
