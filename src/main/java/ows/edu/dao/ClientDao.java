package ows.edu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ows.edu.dto.Client;

@Mapper
public interface ClientDao {
	public List<Client> selectList(Map<String, Object> map);
	
	public List<Client> selectListAll(Map<String, Object> map);
	
	public int statusCnt(int status);
}
