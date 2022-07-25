package ows.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ows.edu.dto.CombineShippingPartner;

@Mapper
public interface OrderItemDao {
	public int sumUnreleasedByStatus(int status);
	/**
	 * @author 최숙희
	 */
	public List<Integer> selectOiNo(Long ordNo);
	/**
	 * @author 최숙희
	 * @param oiNoList
	 * @return
	 */
	public String selectConcatNote(List<Integer> oiNoList);
	/**
	 * @author 최숙희
	 * @param combineShippingPartner
	 * @return
	 */
	public int updateOiUnreleaseQuantity(CombineShippingPartner combineShippingPartner);
}
