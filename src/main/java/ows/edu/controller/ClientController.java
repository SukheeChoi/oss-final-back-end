package ows.edu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	@GetMapping("/")
	public Map<String, Object> getClientNameList() {
		Map<String, Object> m = new HashMap<>();
		List<String> list = new ArrayList<>();
		list = clientService.getlist();
//		log.info("clientName : " + clientName);
		log.info("clientcontroller : " + list);
		m.put("list", list);
		return m;
	}
	
	
//	@GetMapping("/main")
//	public Map<String, Object> getClientNameList(@RequestParam(value="clientName") String clientName) {
//		List<String> clientNameList = new ArrayList<>();
//		clientNameList = clientService.getlist(clientName);
//		log.info("clientName : " + clientName);
//		log.info("clientcontroller : " + clientNameList);
//		
//		Map<String, Object> map = new HashMap<>();
//		map.put("clientName", clientName);
//		return map;
//	}
	
}
