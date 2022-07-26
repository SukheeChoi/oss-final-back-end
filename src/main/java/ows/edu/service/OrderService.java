package ows.edu.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import ows.edu.dto.Client;

@Transactional
public interface OrderService {
	// '주문확인' 페이지의 페이지당 정보를 모두 리턴.
//	public List<String> getOrderNoList(Pager pager);
	public int getTotalOrders();

	// 현황(주문건)
	public int count();

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
