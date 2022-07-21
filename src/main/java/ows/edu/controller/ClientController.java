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
import ows.edu.dto.Pager;
import ows.edu.dto.ClientOrder;
import ows.edu.dto.ClientOrderDetail;
import ows.edu.dto.SelectedOrder;
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
	public Map<String, Object> getFilterList(@RequestBody ClientFilter filterList,
											@RequestParam(value="pageNo", defaultValue="1") int pageNo) {
		log.info(filterList);
		String[] shippingCategory = filterList.getShippingCategory();
		int status = filterList.getStatus();
		boolean orderUnrelease = filterList.isUnrelease();
		Long orderNo = filterList.getOrderNo();
		String clientName = filterList.getClientName();
	
		Map<String, Object> map1 = new HashMap<>();
		map1.put("shippingCategory", shippingCategory);
		map1.put("status", status);
		map1.put("orderUnrelease", orderUnrelease);
		map1.put("orderNo", orderNo);
		map1.put("clientName", clientName);
		map1.put("pageNo", pageNo);
		log.info("clientcontroller - map1: " + map1);

		List<Client> list1 = clientService.getReleaseList(map1);
		List<Client> list2 = clientService.getUnreleaseList(map1);
		
		Map<String, Object> map2 = new HashMap<>();
		map2.put("list1", list1);
		map2.put("list2", list2);
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
		return unreleaseCnt;
	}
	
	@GetMapping("/modal")
	public Map<String, Object> getModal(@RequestParam int clientNo, @RequestParam String orderNo) {
	  //모달 정보
	  Map<String, Object> data = clientModalService.getModal(clientNo, orderNo);
    return data;
	}
	
	@GetMapping("/modalDetail")
	public Map<String, Object> getModalDetail(@RequestParam String orderNo) {
    //상세 내역
    List<ClientOrderDetail> clientOrderDetail = clientModalService.getClientOrderDetailByOrderNo(orderNo);
    
    Map<String, Object> map = new HashMap<>();
    map.put("clientOrderDetail", clientOrderDetail);
    return map;
  }
}
