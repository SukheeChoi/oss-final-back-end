package ows.edu.dto;

import lombok.Data;

/**
 * 출고검수/패킹 진행 페이지의 필터링 정보 담을 객체.
 * @author 최숙희
 */
@Data
public class AfterPickingFilter {
	private String shippingCategory;
	private String shippingWay;
	private String released;
	private String assignee;
	private Long orderNo;
	private String clientName;
	private String shippingDestination;
	private String vendorName;
	private Integer pageNo;
	private Integer pageSize;
	
}
