package ows.edu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
//	//거래처 주문 목록 전체 불러오기
//	@GetMapping("/getListAll")
//	public List<Client> getClientNameList() {
//		List<Client> list = clientService.selectListAll();
//		return list;
//	}
	
	@PostMapping("/getFilterList")
	public Map<String, Object> getClientNameList(@RequestParam(value="shippingCategory", defaultValue="") String shippingCategory,
												@RequestParam(value="status", defaultValue="") int status,
												@RequestParam(value="unreleaseChk", defaultValue="false") String unreleaseChk,
												@RequestParam(value="orderNo", required=false) String orderNo,
												@RequestParam(value="clientName", required=false) String clientName) {

//		log.info("clientcontroller - shippingCategory: " + shippingCategory);
//		log.info("clientcontroller - status: " + status);
//		log.info("clientcontroller - unreleaseChk: " + unreleaseChk);
//		log.info("clientcontroller - orderNo: " + orderNo);
//		log.info("clientcontroller - clientName: " + clientName);
		
//		Map<String, Object> map = new HashMap<>();
//		List<Client> list = null;
//		
//		if(status == 0) {
//			list = clientService.selectListByShippingCategory(
//						shippingCategory,
//						unreleaseChk,
//						orderNo,
//						clientName
//					);
//		} else {
//			list = clientService.selectList(
//						shippingCategory,
//						status,
//						unreleaseChk,
//						orderNo,
//						clientName
//					);
//		}
//		
//		log.info("list : " + list);
//		map.put("list", list);
//		return map;
		
		String[] scType = shippingCategory.split(",");
		List<Client> list = null;
		
		Map<String, Object> map1 = new HashMap<>();
		map1.put("shippingCategory", scType);
		map1.put("unreleaseChk", unreleaseChk);
		map1.put("orderNo", orderNo);
		map1.put("clientName", clientName);
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
		log.info("clientcontroller - shippingCategory: " + shippingCategory);
		log.info("clientcontroller - status: " + status);
		log.info("clientcontroller - unreleaseChk: " + unreleaseChk);
		log.info("clientcontroller - orderNo: " + orderNo);
		log.info("clientcontroller - clientName: " + clientName);
		log.info("clientcontroller - list: " + list);
		log.info("clientcontroller - map2: " + map2);
		log.info("ClientService - map " + map2.keySet());
		return map2;
	}
	
	@GetMapping("/sts")
	public Map<String, Object> statusCnt() {	
		List<Integer> statusCnt = clientService.statusCnt();
		
		Map<String, Object> map = new HashMap<>();
		map.put("statusCnt", statusCnt);
		return map;
	}
	
}
