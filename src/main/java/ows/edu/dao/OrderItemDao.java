package ows.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ows.edu.dto.CombineShippingPartner;

@Mapper
public interface OrderItemDao {
	public int sumUnreleasedByStatus(int status);

	/**
	 * @author 최숙희
	 * @param ordNo 주문번호
	 * @return 주문물품 번호 리스트
	 */
	public List<Integer> selectOiNo(Long ordNo);

	/**
	 * @author 최숙희
	 * @param oiNoList 주문물품번호 리스트
	 * @return 주문번호 기준으로 합쳐진 물품에 대한 메모
	 */
	public String selectConcatNote(List<Integer> oiNoList);

	/**
	 * @author 최숙희
	 * @param combineShippingPartner
	 * @return 업데이트 처리된 행 수
	 */
	public int updateOiUnreleaseQuantity(CombineShippingPartner combineShippingPartner);
}
