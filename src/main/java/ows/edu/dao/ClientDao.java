package ows.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ows.edu.dto.Client;

@Mapper
public interface ClientDao {
  public List<String> showList();
  public Client searchByNo(int clientNo);
}
