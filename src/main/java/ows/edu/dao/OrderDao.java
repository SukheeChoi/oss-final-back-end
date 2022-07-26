package ows.edu.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDao {

	/**
	 * @author 최숙희
	 * @param status           주문 처리 상태값.
	 * @param shippingCategory 배송 구분.
	 * @return int 필터링된 주문 건수.
	 */
	public HashMap<String, Object> countSummaryByStatus(int status);

	public int countProgressOrder();

	// 피킹완료건 -> 전체
	public int pickingDoneCount();

	// 피킹완료건 -> 일반
	public int pickDnCommonCount();

	// 피킹완료건 -> 긴급
	public int pickDnEmergencyCount();

	// 출고검수/패킹건 -> 전체
	public int rlsInspPackingCount();

	// 출고검수/패킹건 -> 일반
	public int rlsInspPackCommonCount();

	// 출고검수/패킹건 -> 긴급
	public int rlsInspPackEmergencyCount();

	// 출고검수완료 버튼 클릭
	public int updateOrdSts(long orderNo);

}
