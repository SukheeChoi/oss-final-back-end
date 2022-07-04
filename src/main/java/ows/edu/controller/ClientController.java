package ows.edu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import ows.edu.dto.Client;
import ows.edu.service.ClientService;

@RestController
@RequestMapping("/client")
@Log4j2
public class ClientController {
	
	@Resource
	ClientService clientService;
	
	//배송구분, 주문 단계, 미출고로 필터링
	@GetMapping("/getFilterList")
	public Map<String, Object> getClientNameList(@RequestParam(value="shippingCategory") String shippingCategory,
												@RequestParam(value="status") int status,
												@RequestParam(value="unreleaseChk", defaultValue="false") String unreleaseChk
//												,
//												@RequestParam(value="orderNo", required=false) String orderNo,
//												@RequestParam(value="clientName", required=false) String clientName
												) {
		String[] scType = shippingCategory.split(",");
		List<Client> list = null;
		
		Map<String, Object> map1 = new HashMap<>();
		map1.put("shippingCategory", scType);
		map1.put("unreleaseChk", unreleaseChk);
//		map1.put("orderNo", orderNo);
//		map1.put("clientName", clientName);
		log.info("clientcontroller - map1: " + map1);

		if(status == 0) {
			list = clientService.selectListByShippingCategory(map1);
			log.info("list : " + list + "status : " + status);
		} else {
			map1.put("status", status);
			list = clientService.selectList(map1);
		}
		
		Map<String, Object> map2 = new HashMap<>();
		map2.put("list", list);
		return map2;
	}
	
	
	//주문 단계 별 건수 요청
	@GetMapping("/sts")
	public Map<String, Object> statusCnt() {	
		List<Integer> statusCnt = clientService.statusCnt();
		
		Map<String, Object> map = new HashMap<>();
		map.put("statusCnt", statusCnt);
		return map;
	}
	
}
