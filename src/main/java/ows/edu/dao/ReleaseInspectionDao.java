package ows.edu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ows.edu.dto.ReleaseInspection;

@Mapper
public interface ReleaseInspectionDao {
	// 완료되지 않은 주문에 대한 출고검수 진행 건수 조회.
	public int countReleaseInspection();
	// 출고검수 단계의 미출고 건수 조회.
	public int sumUnreleased();
	// 출고검수 단계의 긴급 배송 건수 조회.
	public int countExpressShipping();
	// 출고검수 단계의 일반 배송 건수 조회.
	public int countNormalShipping();
	
	// 출고검수/패킹 진행 페이지에 대한 전체 조회.
	public List<ReleaseInspection> selectAfterPickingList(
		String shippingCategory
		, String shippingWay
		, String released
		, String assignee
//		, int orderNo
		
		, String strOrderNo
		
		, String clientName
		, String shippingDestination
		, String vendorName
	);

	//========================현주========================
	//검수수량 업데이트
	public int releaseInspectionQtyUpdate(String releaseCode);
	
	//미출고수량 업데이트
	public int unRleaseQtyUpdate(String releaseCode);
	
	//스캔
	public ReleaseInspection scan(@Param("code") String releaseCode, @Param("kind") String kind);
	
	//출고검수수량 업데이트
	public int update(Map<String, Integer> map);
}

