package ows.edu.dao;

import org.apache.ibatis.annotations.Mapper;

import ows.edu.dto.CombineShippingPartner;

@Mapper
public interface OrderItemDao {
	public int sumUnreleasedByStatus(int status);
	public int updateOiUnreleaseQuantity(CombineShippingPartner combineShippingPartner);
}
