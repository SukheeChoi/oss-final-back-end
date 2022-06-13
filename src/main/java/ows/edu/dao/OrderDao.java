package ows.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ows.edu.dto.Pager;

@Mapper
public interface OrderDao {
	// 전체 orderitemno 갯수 조회.
	public int count();
	public List<String> selectByPage(Pager pager);
}
