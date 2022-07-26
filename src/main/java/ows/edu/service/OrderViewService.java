package ows.edu.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ows.edu.dao.OrderViewDao;
import ows.edu.dto.OrderFilter;
import ows.edu.dto.OrderStatus;
import ows.edu.dto.OrderView;
import ows.edu.dto.Pager;

@Service
public class OrderViewService {

	@Autowired
	private OrderViewDao orderViewDao;

	/**
	 * 주문확인 현황을 반환함
	 * 
	 * @author 이동현
	 * @return 전체/오스템/협력사합배송/협력사직배송/미출고 내역을 반환
	 */
	public OrderStatus getStatus() {
		OrderStatus orderStatus = new OrderStatus();
		orderStatus.setTotal(orderViewDao.countAll());
		orderStatus.setOsstem(orderViewDao.countOsstem());
		orderStatus.setVendorShippingPlus(orderViewDao.countVendorPlus());
		orderStatus.setVendorShippingDir(orderViewDao.countVendorDir());
		orderStatus.setUnreleased(orderViewDao.countunleased());
		return orderStatus;
	}

	/**
	 * 필터링 조건에 맞는 주문확인 목록을 반환함
	 * 
	 * @author 이동현
	 * @param orderFilter 회사/배송구분/미출고/검색조건/검색내용을 포함
	 * @param pageNo      페이지 번호
	 * @param pageSize    페이지당 행 수
	 * @return 전체 데이터 개수와 페이지에 해당하는 주문확인 목록 반환
	 */
	public Map<String, Object> getListByFilter(OrderFilter orderfilter, int pageNo, int pageSize) {
		List<OrderView> list = new ArrayList<>();

		orderfilter = setData(orderfilter);
		int totalRows = orderViewDao.count(orderfilter);
		Pager pager = new Pager(pageSize, 5, totalRows, pageNo);
		list.addAll(orderViewDao.selectByFilter(orderfilter, pager));

		// 주문 상태에 맞게 미출고 처리
		for (OrderView ov : list) {
			// 피킹 지시 단계
			if (ov.getOrderStatus() == 2) {
				ov.setPickingDirectionUnrelease(ov.getUnrelease());

				// 피킹 단계
			} else if (ov.getOrderStatus() == 3) {
				ov.setPickingDirectionUnrelease(0);
				ov.setPickingUnrelease(ov.getUnrelease());
			}
		}

		Map<String, Object> map = new HashMap<>();
		map.put("data", list);
		map.put("totalCount", totalRows);
		return map;
	}

	/**
	 * 조건에 해당하는 데이터 정제
	 * 
	 * @author 이동현
	 * @param orderfilter 회사/배송구분/미출고/검색조건/검색내용을 포함
	 * @return 오스템제품/오스템상품/협력사상품(합배송)/협력사상품(직배송)/배송구분/미출고 객체 반환
	 */
	private OrderFilter setData(OrderFilter orderfilter) {

		// 회사 제품 조건 검색
		String[] company = orderfilter.getCompany();
		// 오스템 제품
		if (Arrays.asList(company).contains("osstemItem")) {
			orderfilter.setItemOSS(1);
		}
		// 오스템 상품
		if (Arrays.asList(company).contains("osstemProduct")) {
			orderfilter.setItemOSSPRO(1);
		}
		// 협력사 직배송
		if (Arrays.asList(company).contains("vendorproductDir")) {
			orderfilter.setItemVND(1);
		}
		// 협력사 합배송
		if (Arrays.asList(company).contains("vendorproductPlus")) {
			orderfilter.setItemVNDPLUS(1);
		}

		// 긴급 일반 조건 검색
		String[] shippgingWay = orderfilter.getShippingway();
		if (shippgingWay.length != 2) {
			if (Arrays.asList(shippgingWay).contains("emergency")) {
				orderfilter.setShippingCategory(1);
			}
			if (Arrays.asList(shippgingWay).contains("normal")) {
				orderfilter.setShippingCategory(2);
			}
		}

		// 출고 미출고 조건 검색
		String[] unReleased = orderfilter.getUnreleased();
		if (unReleased.length != 2) {
			if (Arrays.asList(unReleased).contains("released")) {
				orderfilter.setPickingdirectionUnreleased(1);
			}
			if (Arrays.asList(unReleased).contains("unreleased")) {
				orderfilter.setPickingdirectionUnreleased(2);
			}
		}
		return orderfilter;
	}

}
