//김예원
package ows.edu.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import ows.edu.dto.Client;
import ows.edu.dto.ClientDetail;
import ows.edu.dto.ClientFilter;
import ows.edu.dto.PastOrder;
import ows.edu.dto.PastOrderDetail;
import ows.edu.dto.RecentOrder;
import ows.edu.service.ClientModalService;
import ows.edu.service.ClientService;

@RestController
@RequestMapping("/client")
@Log4j2
public class ClientController {
	@Resource
	ClientService clientService;
	
	@Resource
	private ClientModalService clientModalService;
	
	//배송구분, 주문 단계, 미출고로 필터링	
	@PostMapping("/getFilterList")
	public Map<String, Object> getFilterList(@RequestBody ClientFilter filterList) {
		log.info(filterList);
		String[] shippingCategory = filterList.getShippingCategory();
		int status = filterList.getStatus();
		boolean orderUnrelease = filterList.isUnrelease();
		Long orderNo = filterList.getOrderNo();
		String clientName = filterList.getClientName();
		
		log.info("shippingCategory : " + Arrays.toString(shippingCategory));
		log.info("status : " + status);
		log.info("orderUnrelease : " + orderUnrelease);
		log.info("orderNo : " + orderNo);
		log.info("clientName : " + clientName);
		List<Client> list = null;
	
		Map<String, Object> map1 = new HashMap<>();
		map1.put("shippingCategory", shippingCategory);
		map1.put("orderUnrelease", orderUnrelease);
		map1.put("orderNo", orderNo);
		map1.put("clientName", clientName);
		log.info("clientcontroller - map1: " + map1);

		if(status == -1) {
			list = clientService.getListByShippingCategory(map1);
			log.info("list1 : " + list);
		} else {
			map1.put("status", status);
			list = clientService.getList(map1);
			log.info("list2 : " + list);
		}
		
		Map<String, Object> map2 = new HashMap<>();
		map2.put("list", list);
		log.info("map2 : " + map2);
		return map2;
	}
	
	
	//주문 단계 별 건수 요청
	@GetMapping("/sts")
	public Map<String, Object> statusCnt() {	
		List<Integer> statusCnt = clientService.getstatusCnt();
		
		Map<String, Object> map = new HashMap<>();
		map.put("statusCnt", statusCnt);
		return map;
	}
	
	//미출고 건수 조회
	@GetMapping("/unreleaseCnt")
	public int unreleaseCnt() {
		int unreleaseCnt = clientService.unreleaseCnt();
		log.info("unreleaseCnt : " + unreleaseCnt);
		return unreleaseCnt;
	}
	
	@GetMapping("/modal")
	public Map<String, Object> getModal(@RequestParam int clientNo, @RequestParam String orderNo) {
	  
	  Map<String, Object> map = new HashMap<>();
	  //거래처 정보
	  ClientDetail clientDetail = clientModalService.getClientDetailByClientNo(clientNo);

	  //진행 주문 정보
	  List<RecentOrder> recentOrder = clientModalService.getRecentOrderByOrderNo(orderNo);
	  
	  //과거 주문 이력
	  List<PastOrder> pastOrder = clientModalService.getPastOrderListByClientNo(clientNo);
	  
    map.put("clientDetail", clientDetail);
    map.put("recentOrder", recentOrder);
    map.put("pastOrder", pastOrder);
    return map;
	}
	
	@GetMapping("modalDetail")
	public Map<String, Object> getModalDetail(@RequestParam String orderNo) {
    
    Map<String, Object> map = new HashMap<>();

    //상세 내역
    List<PastOrderDetail> pastOrderDetail = clientModalService.getPastOrderDetailByOrderNo(orderNo);

    map.put("pastOrderDetail", pastOrderDetail);

    return map;
  }
}
