package ows.edu.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDao {
	// 미완료된 주문 건수 조회.
	public int countProgressOrder();

	//피킹완료건 -> 전체
	public int pickingDoneCount();
	
	//피킹완료건 -> 일반
	public int pickDnCommonCount();
	
	//피킹완료건 -> 긴급
	public int pickDnEmergencyCount();
	
	//출고검수/패킹건 -> 전체
	public int rlsInspPackingCount();
	
	//출고검수/패킹건 -> 일반
	public int rlsInspPackCommonCount();
	
	//출고검수/패킹건 -> 긴급
	public int rlsInspPackEmergencyCount();
	

}

