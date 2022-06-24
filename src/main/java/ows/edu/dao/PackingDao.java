package ows.edu.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PackingDao {

	int countPackingNo();

	int sumUnreleased();

	
}
