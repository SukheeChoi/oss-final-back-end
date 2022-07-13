package ows.edu.dao;

import org.apache.ibatis.annotations.Mapper;

import ows.edu.dto.CombineShipping;

@Mapper
public interface OrderItemDao {
	public int updateOiUnreleaseQuantity(CombineShipping combineShipping);
}
