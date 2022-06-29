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
	
	@GetMapping("/getFilterList")
	public Map<String, Object> getClientNameList(@RequestParam(value="shippingCategory") String shippingCategory,
												@RequestParam(value="status") int status,
												@RequestParam(value="unrelease") String unrelease) {
		log.info("!!!!!!!!!!!!!!!!!!!!!");
		log.info("clientcontroller - shippingCategory: " + shippingCategory);
		log.info("clientcontroller - status: " + status);
		System.out.println("clientcontroller - shippingCategory: " + shippingCategory);
		System.out.println("clientcontroller - status: " + status);
		String[] scType = shippingCategory.split(",");
		List<Client> list = null;
		
		Map<String, Object> map1 = new HashMap<>();
		map1.put("shippingCategory", scType);
		log.info("!!!!!````````````````!!!!!!!!!!!!!!!");
		if(status == 0) {
			list = clientService.selectListByShippingCategory(map1);
			log.info("list : " + list + "status : " + status);
		} else {
			map1.put("status", status);
			list = clientService.selectList(map1);
		}
		
		Map<String, Object> map2 = new HashMap<>();
		map2.put("list", list);
		map2.put("shippingCategory", shippingCategory);
		map2.put("status", status);
		map2.put("unrelease", unrelease);
		log.info("clientcontroller - list: " + list);
		System.out.println("clientcontroller - list: " + list);
		
		log.info("clientcontroller - map2: " + map2);
		log.info("clientcontroller - unrelease: " + unrelease);
		log.info("!!!!!````````````````!!!!!!!!!!1111111111!!!!!");
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
