package ows.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ows.edu.dto.Client;

@Mapper
public interface ClientDao {
//  public Client searchByNo(int clientNo);
  
//  public int count();
	
  public List<Client> selectList();
  
  public int statusCnt(int status);
}
