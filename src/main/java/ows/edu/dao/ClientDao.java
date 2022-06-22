package ows.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ows.edu.dto.Client;
import ows.edu.dto.Pager;
import ows.edu.dto.ReleaseInspectionView;

@Mapper
public interface ClientDao {
  public Client searchByNo(int clientNo);
  
  public int count();
  public List<String> selectByPage(Pager pager);
}
