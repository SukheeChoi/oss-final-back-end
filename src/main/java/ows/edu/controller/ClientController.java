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
	
	//거래처 목록 불러오기
//	@GetMapping("/")
//	public Map<String, Object> getClientNameList() {
//		Map<String, Object> map = new HashMap<>();
//		List<Client> list = clientService.selectList();
//		map.put("list", list);
//		log.info("clientcontroller - map: " + map);
//		return map;
//	}
	
	@GetMapping("/")
	public Map<String, Object> getClientNameList(@RequestParam(value="shippingCategory") String shippingCategory,
												@RequestParam(value="status") int status) {
		log.info("clientcontroller - shippingCategory: " + shippingCategory);
		log.info("clientcontroller - status: " + status);
		String[] scType = shippingCategory.split(",");
		List<Client> list = null;
		Map<String, Object> map1 = new HashMap<>();
		map1.put("shippingCategory", scType);
		
		if(status == 0) {
			list = clientService.selectListAll(map1);
			log.info("list : " + list + "status : " + status);
		} else {
			map1.put("status", status);
			list = clientService.selectList(map1);
		}
		
		Map<String, Object> map2 = new HashMap<>();
		map2.put("list", list);
		map2.put("shippingCategory", shippingCategory);
		map2.put("status", status);
		log.info("clientcontroller - list: " + list);
		log.info("clientcontroller - map2: " + map2);
		return map2;
	}
	
	@GetMapping("/sts")
	public Map<String, Object> statusCnt() {	
		List<Integer> statusCnt =clientService.statusCnt();
		
		Map<String, Object> sMap = new HashMap<>();
		sMap.put("statusCnt", statusCnt);
		return sMap;
	}
	
}
